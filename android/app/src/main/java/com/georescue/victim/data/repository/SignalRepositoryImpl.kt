package com.georescue.victim.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.georescue.victim.domain.models.SignalType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Writes victim distress signals to Firestore `signals/` collection.
 *
 * Schema (matches architecture.md §3 exactly):
 * {
 *   "userId":    string,
 *   "type":      "SOS" | "INACTIVITY",
 *   "location":  { "lat": string, "lng": string },
 *   "timestamp": timestamp (server-side)
 * }
 *
 * The backend Cloud Function watches this collection and creates
 * the actual `incidents/` document — the client never writes incidents directly.
 */
@Singleton
class SignalRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val fusedLocation: FusedLocationProviderClient
) : SignalRepository {

    @SuppressLint("MissingPermission")
    override suspend fun sendSignal(type: SignalType): Result<String> {
        return try {
            val userId = auth.currentUser?.uid
                ?: return Result.failure(IllegalStateException("User not authenticated"))

            // Fetch last known location (best-effort; null-safe)
            val location = fusedLocation.lastLocation.await()
            val latStr = location?.latitude?.toString() ?: "0.0"
            val lngStr = location?.longitude?.toString() ?: "0.0"

            val latDouble = location?.latitude?.toDouble() ?: 0.0
            val lngDouble = location?.longitude?.toDouble() ?: 0.0

            val signalData = mapOf(
                "userId"    to userId,
                "type"      to type.name,              // "SOS" or "INACTIVITY"
                "location"  to mapOf(
                    "lat"   to latStr,
                    "lng"   to lngStr
                ),
                "timestamp" to FieldValue.serverTimestamp()
            )

            //Log.d("SignalRepository", "Sending signal")

            val docRef = firestore.collection("signals").add(signalData).await()
            Log.d("SignalRepository", "Signal sent: ${docRef.id} | type=${type.name}")

            // ─── MOCK INCIDENT FOR LOCAL TESTING ──────────────────────────────
            // Since the backend Cloud Function isn't running, we manually create
            // the incident document so the UI's IncidentObserver reacts instantly.
            val mockIncidentId = "INC-${System.currentTimeMillis()}"
            val mockIncidentData = mapOf(
                "type" to "LANDSLIDE",
                "triggerType" to type.name,
                "status" to "ASSIGNED", // Directly jump to ASSIGNED to show the new UI
                "location" to mapOf("lat" to latDouble, "lng" to lngDouble),
                "createdAt" to FieldValue.serverTimestamp(),
                "reportedBy" to userId,
                "assignedResponderId" to "Unit 402",
                "confidenceScore" to 0.98,
                "timeline" to mapOf(
                    "assignedAt" to FieldValue.serverTimestamp()
                )
            )
            firestore.collection("incidents").document(mockIncidentId).set(mockIncidentData).await()
            Log.d("SignalRepository", "Mock incident created: $mockIncidentId")
            // ──────────────────────────────────────────────────────────────────

            Result.success(docRef.id)

        } catch (e: Exception) {
            Log.e("SignalRepository", "Failed to send signal: ${e.message}", e)
            Result.failure(e)
        }
    }
}
