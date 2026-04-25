import {Timestamp} from "firebase-admin/firestore";

export interface Location {
  lat: number;
  lng: number;
}

export interface User {
  name: string;
  role: "victim" | "responder" | "admin";
  status: "active" | "inactive";
  lastKnownLocation: {
    lat: number;
    lng: number;
    updatedAt: Timestamp;
  };
}

export interface Signal {
  userId: string;
  type: "SOS" | "INACTIVITY";
  location: Location;
  timestamp: Timestamp;
}

export interface IncidentTimeline {
  assignedAt?: Timestamp;
  respondedAt?: Timestamp;
  resolvedAt?: Timestamp;
}

export interface Incident {
  type: "landslide" | "earthquake";
  triggerType: "manual" | "auto";
  status: "CREATED" | "ASSIGNED" | "RESPONDING" | "RESOLVED";
  location: Location;
  createdAt: Timestamp | FirebaseFirestore.FieldValue;
  reportedBy: string;
  assignedResponderId: string | null;
  timeline: IncidentTimeline;
}
