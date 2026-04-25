import { getFirestore, FieldValue } from "firebase-admin/firestore";
import * as logger from "firebase-functions/logger";
import { Incident } from "../../types";

const db = getFirestore();

export async function updateIncidentState(
  incidentId: string,
  event: "ARRIVED" | "COMPLETE"
): Promise<void> {
  try {
    const incidentRef = db.collection("incidents").doc(incidentId);
    const incidentSnapshot = await incidentRef.get();

    if (!incidentSnapshot.exists) {
      logger.error(`Incident ${incidentId} not found.`);
      return;
    }

    const incident = incidentSnapshot.data() as Incident;
    const currentStatus = incident.status;

    let updateData: Record<string, any> | null = null;

    if (currentStatus === "ASSIGNED" && event === "ARRIVED") {
      updateData = {
        status: "RESPONDING",
        "timeline.respondedAt": FieldValue.serverTimestamp(),
      };
    } else if (currentStatus === "RESPONDING" && event === "COMPLETE") {
      updateData = {
        status: "RESOLVED",
        "timeline.resolvedAt": FieldValue.serverTimestamp(),
      };
    }

    if (updateData) {
      await incidentRef.update(updateData);
      logger.info(
        `Incident ${incidentId} successfully transitioned to ${updateData.status} triggered by event ${event}.`
      );
    } else {
      logger.info(
        `Invalid transition ignored for incident ${incidentId}. Current status: ${currentStatus}, Event: ${event}.`
      );
    }
  } catch (error) {
    logger.error(`Error updating state for incident ${incidentId}:`, error);
    throw error;
  }
}
