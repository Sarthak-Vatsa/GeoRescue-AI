# GeoRescue AI — Web Dashboard Architecture

## 1. Purpose

The dashboard is a real-time monitoring interface.

It:

* reads incident data from Firestore
* displays system state
* reacts to backend updates

It does NOT:

* implement business logic
* modify incident state directly
* perform assignment or decision-making

---

## 2. Data Source

All data comes from Firestore:

Collection:
incidents

Schema:

* id
* status
* location { lat, lng }
* assignedResponderId
* createdAt
* timeline { assignedAt, respondedAt, resolvedAt }

---

## 3. Data Flow

Firestore (backend writes)
↓
onSnapshot listener (frontend)
↓
React state
↓
UI updates

---

## 4. Responsibilities

Frontend:

* render incident list
* render map markers
* reflect status changes
* provide filters (active / all)

Backend:

* create incidents
* assign responders
* update state transitions

---

## 5. Constraints

* No mock data
* No REST APIs
* No server-side logic
* Must use real-time Firestore listeners
* UI must map strictly to Incident schema

---

## 6. Core Principle

Frontend observes.
Backend decides.
