import { initializeApp } from "firebase-admin/app";
import { setGlobalOptions } from "firebase-functions/v2/options";

// Initialize the Firebase Admin SDK
initializeApp();

// Set global options for cloud functions
setGlobalOptions({ maxInstances: 10 });

// Export triggers
export { onSignalCreated } from "./modules/signals/onSignalCreated";

export interface User {
    name: string;
    role: "victim" | "responder" | "admin";
    status: "active" | "inactive";
    lastKnownLocation?: {
        lat: number;
        lng: number;
        updatedAt: FirebaseFirestore.Timestamp;
    };
}

export { handleStateEvent } from "./modules/state/onStateEvent";