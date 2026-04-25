import { getFirestore, FieldValue } from "firebase-admin/firestore";
import * as logger from "firebase-functions/logger";
import { User } from "../../types";

const db = getFirestore();

// Haversine distance calculation in kilometers
function calculateDistance(lat1: number, lon1: number, lat2: number, lon2: number): number {
  const R = 6371; // Radius of the earth in km
  const dLat = deg2rad(lat2 - lat1);
  const dLon = deg2rad(lon2 - lon1);
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
    Math.sin(dLon / 2) * Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  const d = R * c; // Distance in km
  return d;
}

function deg2rad(deg: number): number {
  return deg * (Math.PI / 180);
}

export async function assignResponder(
  incidentId: string,
  incidentLocation: { lat: number; lng: number }
): Promise<void> {
  try {
    const respondersSnapshot = await db.collection("users")
      .where("role", "==", "responder")
      .where("status", "==", "active")
      .get();

    if (respondersSnapshot.empty) {
      logger.info(`No active responders found for incident ${incidentId}`);
      return;
    }

    let nearestResponderId: string | null = null;
    let shortestDistance = Infinity;

    for (const doc of respondersSnapshot.docs) {
      const responder = doc.data() as User;

      const loc = responder.lastKnownLocation;
      if (!loc || loc.lat == null || loc.lng == null) {
        continue;
      }

      const distance = calculateDistance(
        incidentLocation.lat,
        incidentLocation.lng,
        loc.lat,
        loc.lng
      );

      if (distance < shortestDistance) {
        shortestDistance = distance;
        nearestResponderId = doc.id;
      }
    }

    if (!nearestResponderId) {
      logger.info(`No responders with valid locations found for incident ${incidentId}`);
      return;
    }

    await db.collection("incidents").doc(incidentId).update({
      assignedResponderId: nearestResponderId,
      status: "ASSIGNED",
      "timeline.assignedAt": FieldValue.serverTimestamp()
    });

    logger.info(`Successfully assigned responder ${nearestResponderId} to incident ${incidentId}`);
  } catch (error) {
    logger.error(`Error assigning responder to incident ${incidentId}:`, error);
    return; // Propagate error per MVP simple logic
  }
}
