package com.georescue.victim.data.repository

import android.util.Log
import com.georescue.victim.domain.models.GeoPoint
import com.georescue.victim.domain.models.Incident
import com.georescue.victim.domain.models.IncidentStatus
import com.georescue.victim.domain.models.IncidentTimeline
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Attaches a real-time Firestore snapshot listener on the `incidents` collection,
 * filtered to documents where `reportedBy == currentUserId`.
 *
 * The app is READ-ONLY on incidents — state transitions (CREATED → ASSIGNED → RESPONDING → RESOLVED)
 * are performed exclusively by the backend. This observer surfaces those changes to the UI.
 */
@Singleton
class IncidentObserver @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) {
    private val _currentIncident = MutableStateFlow<Incident?>(null)

    /** Latest active incident for the authenticated user. Null if none exists. */
    val currentIncident: StateFlow<Incident?> = _currentIncident.asStateFlow()

    private var listenerRegistration: ListenerRegistration? = null

    /**
     * Attaches the Firestore snapshot listener.
     * Safe to call multiple times — will not duplicate listeners.
     */
    fun startObserving() {
        if (listenerRegistration != null) return

        val userId = auth.currentUser?.uid ?: run {
            Log.w("IncidentObserver", "Cannot observe — user not authenticated")
            return
        }

        Log.d("IncidentObserver", "Starting incident listener for userId=$userId")

        listenerRegistration = firestore
            .collection("incidents")
            .whereEqualTo("reportedBy", userId)
            // Surface the most recently created incident first
            .orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(1)
            .addSnapshotListener { snapshots, error ->
                if (error != null) {
                    Log.e("IncidentObserver", "Snapshot listener error: ${error.message}", error)
                    return@addSnapshotListener
                }

                val doc = snapshots?.documents?.firstOrNull()
                if (doc == null || !doc.exists()) {
                    _currentIncident.value = null
                    return@addSnapshotListener
                }

                try {
                    val locationMap = doc.get("location") as? Map<*, *>
                    val timelineMap = doc.get("timeline") as? Map<*, *>

                    val incident = Incident(
                        incidentId          = doc.id,
                        type                = doc.getString("type") ?: "",
                        triggerType         = doc.getString("triggerType") ?: "",
                        status              = parseStatus(doc.getString("status")),
                        location            = GeoPoint(
                            lat = (locationMap?.get("lat") as? String)?.toDoubleOrNull() ?: 0.0,
                            lng = (locationMap?.get("lng") as? String)?.toDoubleOrNull() ?: 0.0
                        ),
                        createdAt           = doc.getTimestamp("createdAt") ?: Timestamp.now(),
                        reportedBy          = doc.getString("reportedBy") ?: "",
                        assignedResponderId = doc.getString("assignedResponderId"),
                        confidenceScore     = doc.getDouble("confidenceScore") ?: 0.0,
                        timeline            = IncidentTimeline(
                            assignedAt  = timelineMap?.get("assignedAt") as? Timestamp,
                            respondedAt = timelineMap?.get("respondedAt") as? Timestamp,
                            resolvedAt  = timelineMap?.get("resolvedAt") as? Timestamp
                        )
                    )

                    Log.d("IncidentObserver", "Incident update: id=${incident.incidentId} status=${incident.status}")
                    _currentIncident.value = incident

                } catch (e: Exception) {
                    Log.e("IncidentObserver", "Failed to parse incident document: ${e.message}", e)
                }
            }
    }

    /**
     * Detaches the Firestore snapshot listener and resets state.
     * Call this when the service/ViewModel is destroyed to avoid memory leaks.
     */
    fun stopObserving() {
        listenerRegistration?.remove()
        listenerRegistration = null
        _currentIncident.value = null
        Log.d("IncidentObserver", "Incident listener detached")
    }

    private fun parseStatus(raw: String?): IncidentStatus {
        return try {
            IncidentStatus.valueOf(raw ?: "CREATED")
        } catch (e: IllegalArgumentException) {
            Log.w("IncidentObserver", "Unknown incident status: $raw — defaulting to CREATED")
            IncidentStatus.CREATED
        }
    }
}
