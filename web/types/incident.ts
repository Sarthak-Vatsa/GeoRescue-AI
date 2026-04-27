import { Timestamp } from "firebase/firestore";

export type IncidentStatus = "CREATED" | "ASSIGNED" | "RESPONDING" | "RESOLVED";

export type Incident = {
  id: string;
  status: IncidentStatus;
  location: {
    lat: number;
    lng: number;
  };
  assignedResponderId: string | null;
  createdAt: Timestamp;
  timeline: {
    assignedAt?: Timestamp;
    respondedAt?: Timestamp;
    resolvedAt?: Timestamp;
  };
};
