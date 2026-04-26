# Architecture Framework: GeoRescue AI (Victim Mode)

## 1. Directives (Core Stack & Structure)
* **Environment:** Android Studio project (Root: `GeoRescue Android`).
* **Language:** Kotlin.
* **UI Toolkit:** Jetpack Compose (Material3).
* **Architecture Pattern:** MVVM + Clean Architecture.
* **Directory Structure:**
    * `/.agent`: Skill definition files for Antigravity (YAML/Markdown).
    * `/di`: Hilt modules for Firebase, Location, and Sensor providers.
    * `/data`: Implementation of Signal triggers and Location streaming.
    * `/domain`: UseCases for `CalculateInactivity`, `TriggerSignal`, and `MonitorGeofences`.
    * `/presentation`: Compose screens (Map, SOS Overlay) and ViewModels.
    * `/service`: Foreground services for location streaming and sensor monitoring.
* **Key Dependencies:** Firebase (Firestore, Auth, Messaging), Google Maps SDK, Play Services Geofencing, Hilt, Coroutines.

## 2. Orchestration (Agent Thinking & State Logic)
* **Client-Side Limitation:** The Android app is a **Signal Provider ONLY**. It does not create incidents, assign responders, or change incident status. It strictly:
    1. Sends SOS/Inactivity signals.
    2. Streams live location/battery.
    3. Receives assignment and status updates via Firestore listeners.
* **Safety-First Protocol:** No signal is sent without a manual trigger or a completed, un-cancelled 30-second countdown for auto-detection.
* **Battery Optimization:** Sensors and high-frequency location polling are dormant by default. They are activated only via `GEOFENCE_TRANSITION_ENTER`.
* **Incremental Implementation:** Propose one layer at a time. Await approval before moving to the Kinetic Sensor logic.

3. Database Schema (The Source of Truth)
🔹 [Firestore] users collection
users/{userId}

```json
{
  "name": "string",
  "role": "victim",
  "status": "active" | "inactive",
  "lastKnownLocation": {
    "lat": "string",
    "lng": "string",
    "updatedAt": "timestamp"
  }
}
🔹 [Firestore] incidents collection (Read-Only for Victim)
incidents/{incidentId}

```json
{
  "type": "landslide" | "earthquake",
  "triggerType": "manual" | "auto",
  "status": "CREATED" | "ASSIGNED" | "RESPONDING" | "RESOLVED",
  "location": { "lat": "string", "lng": "string" },
  "createdAt": "timestamp",
  "reportedBy": "userId",
  "assignedResponderId": "string | null",
  "confidenceScore": "number", 
  "timeline": {
    "assignedAt": "timestamp?",
    "respondedAt": "timestamp?",
    "resolvedAt": "timestamp?"
  }
}
```

🔹 [Firestore] risk_zones collection
risk_zones/{zoneId}

JSON
{
  "name": "string",
  "type": "landslide" | "earthquake",
  "center": { "lat": "string", "lng": "string" },
  "radius": "number",
  "severity": "low" | "medium" | "high",
  "updatedAt": "timestamp"
}
🔹 [Firestore] signals (Victim Writes)
signals/{signalId}

JSON
{
  "userId": "string",
  "type": "SOS" | "INACTIVITY",
  "location": { "lat": "string", "lng": "string" },
  "timestamp": "timestamp"
}
🔹 [RTDB] presence (Connection Status)
presence/{userId}

JSON
{
  "isOnline": "boolean",
  "lastSeen": "ServerValue.TIMESTAMP"
}
🔹 [RTDB] locations (High-Frequency Stream)
locations/{userId}

JSON
{
  "lat": "number",
  "lng": "number",
  "battery": "number",
  "updatedAt": "ServerValue.TIMESTAMP"
}

## 4. API & State Contracts

### **Signal Trigger (Victim → Backend)**
The client triggers an incident by writing a document to the `signals` collection. The backend then validates this signal and creates the actual `incident` object.

### **State Transitions (Backend Enforced)**
The client observes these transitions but **never** writes them:
`CREATED` → `ASSIGNED` → `RESPONDING` → `RESOLVED`.

## 5. Integrated Agent Skills
The agent must utilize the `.agent` directory for the following:
* **`fetch-compose-docs`**: Fetch latest API guidelines before writing UI code.
* **`sensor-math-audit`**: Audit kinetic magnitude math: $Amag = \sqrt{x^2 + y^2 + z^2}$.
* **`firestore-contract-sync`**: Ensure `Signal` and `LiveStatus` writes match Section 3 exactly.

## 6. Execution (Phased Build Plan)

#### Phase 1: Foundation & Dependency Injection (Day 1)
The goal is to establish the "Contract" and the DI graph.
* **Module Scaffolding:** Create the package structure: `data.repository`, `data.sources`, `domain.models`, `domain.usecases`, `presentation.screens`, `service`.
* **Domain Models:** Create immutable Kotlin data classes for `Signal`, `Incident`, `RiskZone`, and `LiveStatus`.
* **Hilt Provision:**
    * `FirebaseModule`: Provide `FirebaseFirestore` and `FirebaseAuth` instances.
    * `LocationModule`: Provide `FusedLocationProviderClient` and `GeofencingClient`.
* **Auth Wrapper:** Implement a simple anonymous login or Google Sign-In to ensure every write has a valid `userId`.

Phase 2: The Gatekeeper & RTDB Presence (Day 1.5–2)
RiskZoneRepository: Fetch risk zones from Firestore.

GeofenceManager: Map risk zones to Geofence objects and register them.

DetectionService (RTDB Integration): * Implement Presence System: Use RTDB onDisconnect() to automatically set isOnline = false and capture lastSeen if connection is lost.

On GEOFENCE_TRANSITION_ENTER: Start DetectionService and set RTDB isOnline = true.

On GEOFENCE_TRANSITION_EXIT: Stop DetectionService and set RTDB isOnline = false.

#### Phase 3: The Intelligence Layer (Kinetic AI) (Day 2.5–3)
This is your core differentiator. Use the **Sensor Math Audit** skill here.
* **`SensorRepository`:** Use `Flow` to stream `LinearAcceleration` data.
* **`InactivityUseCase`:**
    * Calculate magnitude: $Amag = \sqrt{x^2 + y^2 + z^2}$.
    * Implement a rolling variance check. If variance remains below $0.5m/s^2$ for the duration threshold, emit an `IMMOBILIZED` state.
* **The Failsafe Timer:** Build a `CountDownTimer` (30 seconds). If the user doesn't interact with the UI, call the `SignalUseCase`.

#### Phase 4: Communication & Telemetry (Day 3.5–4)
Focus on the "Signal Provider" role.
* **`SignalRepository`:**
    * `sendSignal(type: SignalType)`: Writes a new document to the `signals/` collection.
* **`LiveStatusStreamer`:** Inside the `DetectionService`, set up a coroutine that writes to `users/{userId}/live_status/current` every 10–30 seconds.
* **`IncidentObserver`:**
    * Implement a Firestore snapshot listener on `incidents/` where `reportedBy == currentUserId`.
    * Pipe these updates to a `StateFlow` so the UI reacts when the `status` changes from `CREATED` to `ASSIGNED`.

#### Phase 5: The UI Layer (Compose) (Day 4.5–5)
Use the **Compose Docs Retrieval** skill for modern, reactive layouts.
* **`MainMapScreen`:**
    * Integrate Google Maps SDK.
    * Draw `Circle` overlays for each `RiskZone`.
    * Display a reactive "Safety Banner" (Green: Safe, Yellow: Monitoring, Red: Signal Sent).
* **`SOSOverlayScreen` (The "Panic" UI):**
    * A high-priority overlay with a massive "CANCEL" button.
    * Implement haptic feedback (vibration patterns) that increase in intensity as the timer hits zero.
* **`RescueStatusCard`:** A bottom sheet that appears once a signal is sent, showing the real-time status of the rescue (`status`, `assignedResponderId`).