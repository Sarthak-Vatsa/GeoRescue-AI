import { onCall } from "firebase-functions/v2/https";
import { updateIncidentState } from "./updateIncidentState";

export const handleStateEvent = onCall(async (request) => {
    const { incidentId, event } = request.data;

    if (!incidentId || !event) {
        throw new Error("Missing incidentId or event");
    }

    await updateIncidentState(incidentId, event);

    return { success: true };
});