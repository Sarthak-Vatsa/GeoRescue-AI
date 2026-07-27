# 🌍 GeoRescue AI

> **An intelligent disaster-response system that detects victim distress, tracks their location in real time, and automatically connects them with nearby responders.**

GeoRescue AI is an end-to-end emergency response platform designed for disaster scenarios such as **landslides and earthquakes**, where victims may be injured, immobilized, or unable to manually call for help.

The system combines an **Android application, motion-based inactivity detection, geofencing, real-time location tracking, Firebase Cloud Functions, and AI-assisted incident assessment** to automate the workflow from detecting distress to assigning a nearby responder.
<img width="1301" height="597" alt="image" src="https://github.com/user-attachments/assets/45b86f02-102d-43dc-9999-fd6634b505ab" />

<img width="283" height="578" alt="image" src="https://github.com/user-attachments/assets/1e1cf74f-715b-4649-b806-1fc6423357e4" />
<img width="286" height="586" alt="image" src="https://github.com/user-attachments/assets/5893caba-adaf-4dca-89ba-ae2240f736ed" />
<img width="285" height="575" alt="image" src="https://github.com/user-attachments/assets/388b22fd-4a22-43cc-a3e0-4e5596ce451c" />
<img width="286" height="585" alt="image" src="https://github.com/user-attachments/assets/5942438f-c160-471a-a5cb-865213602bff" />




---

## 🚨 The Problem

During a natural disaster, emergency reporting usually depends on the victim being able to actively request help.

However, a victim may be:

* unconscious or immobilized,
* trapped or injured,
* unable to communicate their location,
* unable to make a phone call.

Even when an emergency is reported, identifying and dispatching the nearest available responder can introduce additional delay.

**GeoRescue AI addresses this by allowing the victim's phone to act as an intelligent emergency signal provider.**

---

## 💡 How GeoRescue Works

```text
Victim enters a risk zone
          ↓
GeoRescue activates monitoring
          ↓
Motion + location are monitored
          ↓
Manual SOS ───────────────┐
          OR              │
Inactivity detected       │
          ↓               │
30-second safety countdown│
          ↓               │
      Distress Signal ◄───┘
          ↓
Firebase Cloud Function
          ↓
Signal Validation + Duplicate Protection
          ↓
AI-assisted Severity Assessment
          ↓
Incident Created
          ↓
Nearest Active Responder Identified
          ↓
Responder Assigned
          ↓
CREATED → ASSIGNED → RESPONDING → RESOLVED
```

---

## ✨ Key Features

### 🆘 Manual & Automatic SOS

Users can manually trigger an SOS, while GeoRescue can also detect prolonged inactivity using the device's **linear acceleration sensor**.

The application calculates acceleration magnitude from the three sensor axes:

```text
A = √(x² + y² + z²)
```

A rolling variance check identifies unusually low movement that may indicate immobilization.

---

### ⏱️ False-Alarm Protection

Automatic detection does not immediately create an emergency.

When inactivity is detected, GeoRescue starts a **30-second failsafe countdown**, giving the user an opportunity to confirm that they are safe.

If the countdown expires, an `INACTIVITY` distress signal is automatically generated.

---

### 📍 Geofenced Risk Zones

Known disaster-prone regions are stored as risk zones.

GeoRescue uses Android geofencing to activate intensive monitoring when the user enters a risk zone, reducing unnecessary sensor and location usage outside high-risk areas.

---

### 🛰️ Real-Time Victim Telemetry

While monitoring is active, the application streams:

* live location,
* battery level,
* connection/presence information.

Firebase Realtime Database provides the low-latency telemetry layer, while Firestore stores persistent entities such as incidents, users, signals, and risk zones.

---

### 🤖 AI-Assisted Incident Assessment

The backend integrates **Google Gemini** to analyse incoming distress signals and estimate:

* incident severity (`LOW`, `MEDIUM`, `HIGH`),
* confidence score.

A safe fallback is used if AI assessment fails, ensuring that AI failure does not stop the emergency-processing pipeline.

---

### 🚑 Automatic Responder Assignment

After an incident is created, the backend searches for active responders and calculates their distance from the victim using the **Haversine formula**.

The nearest eligible responder is automatically assigned to the incident.

```text
Incident
   │
   ├── distance → Responder A
   ├── distance → Responder B
   └── distance → Responder C
                    ↓
             Nearest Responder
                    ↓
                 ASSIGNED
```

---

### 🔄 Real-Time Incident Lifecycle

Incidents follow a backend-controlled state machine:

```text
CREATED → ASSIGNED → RESPONDING → RESOLVED
```

The Android client observes these changes in real time through Firestore listeners, while the backend remains responsible for authoritative state transitions.

---

## 🏗️ Architecture

```text
┌─────────────────────────────┐
│      Android Victim App     │
│ Kotlin + Jetpack Compose    │
│ Sensors • GPS • Geofencing  │
└──────────────┬──────────────┘
               │
          SOS / Inactivity
               │
               ▼
┌─────────────────────────────┐
│      Firebase Firestore     │
│ Signals • Incidents • Users │
└──────────────┬──────────────┘
               │
        Firestore Trigger
               ▼
┌─────────────────────────────┐
│    Firebase Cloud Functions │
│          TypeScript         │
│                             │
│ • Validate signal           │
│ • Prevent duplicates        │
│ • AI severity assessment    │
│ • Create incident           │
│ • Find nearest responder    │
│ • Manage incident state     │
└──────────────┬──────────────┘
               │
       ┌───────┴────────┐
       ▼                ▼
 Google Gemini     Active Responders
       │                │
       └───────┬────────┘
               ▼
        Incident Assigned

        ────────────────

 Firebase Realtime Database
      ↓
 Live Location • Battery
      • Presence
```

---

## 🛠️ Tech Stack

| Area                     | Technologies                             |
| ------------------------ | ---------------------------------------- |
| **Mobile**               | Kotlin, Android                          |
| **UI**                   | Jetpack Compose, Material 3              |
| **Architecture**         | MVVM, Clean Architecture                 |
| **Dependency Injection** | Dagger Hilt                              |
| **Reactive Programming** | Kotlin Coroutines, Flow, StateFlow       |
| **Maps & Location**      | Google Maps SDK, Fused Location Provider |
| **Risk Monitoring**      | Android Geofencing API                   |
| **Motion Detection**     | Android SensorManager                    |
| **Authentication**       | Firebase Authentication                  |
| **Database**             | Cloud Firestore                          |
| **Live Telemetry**       | Firebase Realtime Database               |
| **Backend**              | Firebase Cloud Functions v2, TypeScript  |
| **AI**                   | Google Gemini                            |
| **Responder Matching**   | Haversine Distance Algorithm             |

---

## 📂 Project Structure

```text
GeoRescue-AI/
│
├── android/
│   ├── app/src/main/java/com/georescue/victim/
│   │   ├── data/             # Firebase, sensors & telemetry
│   │   ├── di/               # Hilt dependency injection
│   │   ├── domain/           # Models & business logic
│   │   ├── presentation/     # Compose UI & ViewModels
│   │   └── service/          # Background monitoring
│   │
│   └── architecture.md
│
├── backend/
│   ├── functions/src/
│   │   ├── modules/
│   │   │   ├── signals/      # Signal processing
│   │   │   ├── assignment/   # Responder assignment
│   │   │   └── state/        # Incident lifecycle
│   │   └── types/
│   │
│   └── docs/
│       └── architecture.md
│
└── README.md
```

---

## 🚀 Running Locally

### Prerequisites

* Android Studio
* JDK 17
* Android SDK 34
* Node.js & npm
* Firebase CLI
* Firebase project
* Google Maps API key
* Gemini API key

### 1. Clone the repository

```bash
git clone https://github.com/Sarthak-Vatsa/GeoRescue-AI
cd GeoRescue-AI
```

### 2. Configure Android

Register an Android application in Firebase using:

```text
com.georescue.victim
```

Download `google-services.json` and place it at:

```text
android/app/google-services.json
```

Add your Google Maps API key to `android/local.properties`:

```properties
MAPS_API_KEY=YOUR_API_KEY
```

> Never commit `local.properties` or private API credentials.

Open `android/` in Android Studio, sync Gradle, and run the application on an Android device or emulator.

### 3. Configure the backend

```bash
cd backend/functions
npm install
npm run build
```

Configure the required Firebase project and Gemini credentials, then deploy the Cloud Functions:

```bash
firebase login
firebase use <your-project-id>
firebase deploy --only functions
```

---

## 🧠 Engineering Highlights

GeoRescue AI brings together several engineering concepts in a single real-time system:

* **event-driven backend architecture** using Firestore triggers,
* **sensor-based inactivity detection** using rolling motion variance,
* **geofencing** for context-aware and battery-conscious monitoring,
* **real-time distributed state** using Firestore and RTDB,
* **AI-assisted emergency assessment** with graceful fallback,
* **geospatial responder matching** using the Haversine algorithm,
* **backend-enforced state transitions** for consistent incident handling,
* **MVVM + Clean Architecture** for separation of concerns on Android.

---

## 🔮 Future Scope

Potential extensions include:

* dedicated responder mobile application,
* emergency command-centre dashboard,
* push notifications using FCM,
* offline SOS queueing and SMS fallback,
* route and ETA calculation,
* multi-responder dispatch,
* sensor fusion for impact/fall detection,
* disaster/weather API integration,
* responder workload-aware assignment,
* historical incident analytics and heatmaps.

---

## ⚠️ Disclaimer

GeoRescue AI is a **prototype/academic project** demonstrating intelligent emergency-response architecture. It is not intended to replace certified emergency services.

Production deployment would require additional work around security, authentication, offline reliability, emergency escalation, regulatory compliance, sensor validation, and high availability.

---

<p align="center">
  <b>GeoRescue AI</b><br>
  Detect distress. Locate victims. Accelerate response.
</p>
