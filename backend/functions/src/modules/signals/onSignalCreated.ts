import { onDocumentCreated } from "firebase-functions/v2/firestore";
import * as logger from "firebase-functions/logger";
import { getFirestore, FieldValue, Timestamp } from "firebase-admin/firestore";
import { Signal, Incident } from "../../types";
import { assignResponder } from "../assignment/assignResponder";
import { GoogleGenerativeAI } from "@google/generative-ai";


// Initialize Firestore (assumes admin app is initialized in index.ts)
const db = getFirestore();

// Constants
const DUPLICATE_WINDOW_MS = 60 * 1000; // 1 minute window for duplicates
const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY || "");
const model = genAI.getGenerativeModel({ model: "gemini-1.5-flash" });
/**
 * Validates the signal data to ensure all required fields are present and valid.
 */
function isValidSignal(signal: Partial<Signal>): signal is Signal {
  if (!signal.userId || typeof signal.userId !== "string") return false;
  if (!signal.type || !["SOS", "INACTIVITY"].includes(signal.type)) return false;
  if (!signal.location || typeof signal.location.lat !== "number" || typeof signal.location.lng !== "number") {
    return false;
  }

  return true;
}

/**
 * Cloud Function triggered when a new signal document is created.
 * Responsible for validating the signal, preventing duplicates, and creating an incident.
 */
async function getAIAnalysis(
  signalType: string,
  location: { lat: number; lng: number }
): Promise<{ severity: "LOW" | "MEDIUM" | "HIGH"; confidenceScore: number }> {
  try {
    const prompt = `
You are an emergency analysis AI.

Given:
Signal Type: ${signalType}
Location: (${location.lat}, ${location.lng})

Return JSON ONLY:
{
  "severity": "LOW" | "MEDIUM" | "HIGH",
  "confidenceScore": number between 0 and 1
}
`;

    const result = await model.generateContent(prompt);
    const text = result.response.text();

    // Extract JSON safely
    const jsonMatch = text.match(/\{[\s\S]*\}/);
    if (!jsonMatch) throw new Error("Invalid AI response");

    const parsed = JSON.parse(jsonMatch[0]);

    return {
      severity: parsed.severity || "MEDIUM",
      confidenceScore: Number(parsed.confidenceScore) || 0.8,
    };
  } catch (error) {
    console.error("AI failed, using fallback:", error);

    // ✅ SAFE FALLBACK (CRITICAL)
    return {
      severity: "MEDIUM",
      confidenceScore: 0.8,
    };
  }
}

export const onSignalCreated = onDocumentCreated(
  {
    document: "signals/{signalId}",
    region: "asia-southeast1",
  },
  async (event) => {
    const snapshot = event.data;
    if (!snapshot) {
      logger.error("No data associated with the event");
      return;
    }

    const signalData = snapshot.data() as Partial<Signal>;
    const signalId = event.params.signalId;

    // 1. Validate signal data (userId, location, type)
    if (!isValidSignal(signalData)) {
      logger.error(`Invalid signal data for signal ${signalId}`, { signalData });
      // In a real app, we might update the signal document with an error status
      return;
    }

    const { userId, location, type } = signalData;
    const numericLocation = {
      lat: Number(location.lat),
      lng: Number(location.lng),
    };

    const ai = await getAIAnalysis(type, numericLocation);

    try {
      // 2. Prevent duplicate signals within short time window
      // We check if an incident was already created by this user recently
      const recentTime = Timestamp.fromMillis(Date.now() - DUPLICATE_WINDOW_MS);

      const recentIncidentsSnapshot = await db
        .collection("incidents")
        .where("reportedBy", "==", userId)
        .where("createdAt", ">=", recentTime)
        .limit(5)
        .get();

      const isDuplicate = recentIncidentsSnapshot.docs.some((doc) => {
        const data = doc.data();
        return (
          data.location?.lat === location.lat &&
          data.location?.lng === location.lng
        );
      });

      if (isDuplicate) {
        logger.info(`Duplicate signal ignored for user ${userId}`);
        return;
      }

      // Determine incident trigger type based on signal type
      const triggerType: Incident["triggerType"] = type === "SOS" ? "manual" : "auto";

      // Determine default incident type (using earthquake as a placeholder since signal doesn't specify)
      const incidentType: Incident["type"] = "landslide";

      // 3. Create a new document in "incidents"
      const incidentData: Incident = {
        type: incidentType,
        triggerType: triggerType,
        status: "CREATED",
        reportedBy: userId,
        createdAt: FieldValue.serverTimestamp(),
        location: numericLocation,
        assignedResponderId: null,
        confidenceScore: ai.confidenceScore,
        severity: ai.severity,

        timeline: {},
      };

      const incidentRef = await db.collection("incidents").add(incidentData);
      await assignResponder(incidentRef.id, location);

      logger.info(`Successfully created incident ${incidentRef.id} from signal ${signalId}`);
    } catch (error) {
      logger.error(`Error processing signal ${signalId}:`, error);
      throw error; // Let Cloud Functions handle retries if configured
    }
  });
