# Architecture Framework: GeoRescue AI (Backend Mode)

---

## 1. Directives (Core Stack & Structure)

* **Environment:** Firebase Backend (Root: `/backend`)

* **Language:** TypeScript

* **Runtime:** Firebase Cloud Functions

* **Architecture Pattern:** Event-Driven, Modular Functions

* **Directory Structure:**

  * `/functions/src/index.ts` → entry point for all triggers
  * `/functions/src/modules` → domain-specific logic (signals, incidents, assignment)
  * `/functions/src/utils` → helper utilities (validation, distance calculation)
  * `/functions/src/types` → shared TypeScript interfaces

* **Key Dependencies:**

  * Firebase Firestore
  * Firebase Cloud Functions
  * Firebase Admin SDK

---

## 2. Orchestration (System Logic & Authority Model)

* **Backend Responsibility:**
  The backend is the sole authority for:

  * Incident creation
  * State transitions
  * Responder assignment

* **Client Limitation:**
  Clients act strictly as signal providers:

  * Write to `signals` collection
  * Stream live location
  * Observe incident updates

  Clients do not:

  * Create incidents
  * Assign responders
  * Modify incident status

* **Event-Driven Flow:**

  1. Client writes signal to `signals/{signalId}`
  2. Backend trigger executes on document creation
  3. Backend validates signal
  4. Backend creates incident (`status = CREATED`)
  5. Backend assigns responder (`status = ASSIGNED`)
  6. Backend updates incident state over time

---

## 3. Database Schema (Source of Truth)

All backend logic must strictly conform to this schema.

### `users/{userId}`

```json
{
  "name": "string",
  "role": "victim" | "responder" | "admin",
  "status": "active" | "inactive",
  "lastKnownLocation": {
    "lat": "number",
    "lng": "number",
    "updatedAt": "timestamp"
  }
}
```

---

### `incidents/{incidentId}`

```json
{
  "type": "landslide" | "earthquake",
  "triggerType": "manual" | "auto",
  "status": "CREATED" | "ASSIGNED" | "RESPONDING" | "RESOLVED",
  "location": { "lat": "number", "lng": "number" },
  "createdAt": "timestamp",
  "reportedBy": "userId",
  "assignedResponderId": "string | null",
  "timeline": {
    "assignedAt": "timestamp?",
    "respondedAt": "timestamp?",
    "resolvedAt": "timestamp?"
  }
}
```

---

### `signals/{signalId}`

```json
{
  "userId": "string",
  "type": "SOS" | "INACTIVITY",
  "location": { "lat": "number", "lng": "number" },
  "timestamp": "timestamp"
}
```

---

## 4. API & State Contracts

### Signal Ingestion Contract

* Source: Client
* Action: Write to `signals` collection
* Backend trigger processes the signal

---

### Incident Creation Contract

* Triggered by backend only
* Creates new document in `incidents`
* Sets:

  * `status = CREATED`
  * `reportedBy`
  * `createdAt`

---

### Assignment Contract

* Triggered automatically after incident creation
* Backend:

  * selects nearest active responder
  * updates:

    * `assignedResponderId`
    * `status = ASSIGNED`
    * `timeline.assignedAt`

---

### State Transition Contract

* Backend enforced only

Allowed transitions:

```
CREATED → ASSIGNED → RESPONDING → RESOLVED
```

Rules:

* No skipping states
* No backward transitions
* No direct client writes

---

## 5. Core Backend Modules

### Signal Handler

* Trigger: Firestore `onCreate` for `signals`
* Responsibilities:

  * validate input
  * prevent duplicate processing

---

### Incident Engine

* Creates incident documents
* Ensures schema compliance

---

### Assignment Engine

* Fetches responders
* Computes distance
* Assigns nearest responder

---

### State Manager

* Validates transitions
* Updates incident status and timeline

---

## 6. Execution Plan (Phase-Aligned)

### Phase 1: Foundation

* Firebase setup
* Repository structure
* Function scaffolding

---

### Phase 2: Signal → Incident Pipeline

* Firestore trigger on `signals`
* Validation logic
* Incident creation (`status = CREATED`)

---

### Phase 3: Assignment Engine

* Fetch active responders
* Distance calculation
* Automatic assignment (`status = ASSIGNED`)

---

### Phase 4: State Transitions

* Controlled updates to:

  * RESPONDING
  * RESOLVED
* Timeline updates

---

### Phase 5: Real-Time Readiness

* Query optimization
* Index planning
* Dashboard support

---

## 7. System Constraints

* Backend is the only source of truth for incidents
* Client writes are restricted to signals only
* Schema consistency must be maintained across all modules
* All logic must be modular and testable

---

## 8. Success Criteria

* Signal results in incident creation
* Incident is automatically assigned
* State transitions follow defined rules
* Data remains consistent across all components
