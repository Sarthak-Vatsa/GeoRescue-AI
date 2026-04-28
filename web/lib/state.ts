import { httpsCallable } from "firebase/functions";
import { functions } from "./firebase";

export const updateState = async (incidentId: string, event: "ARRIVED" | "COMPLETE") => {
  const fn = httpsCallable(functions, "triggerStateUpdate");
  return await fn({ incidentId, event });
};
