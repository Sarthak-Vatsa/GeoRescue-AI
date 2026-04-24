package com.georescue.victim.data.repository

import com.georescue.victim.domain.models.RiskZone
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RiskZoneRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun getRiskZones(): Flow<List<RiskZone>> = callbackFlow {
        val subscription = firestore.collection("risk_zones")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                
                val zones = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(RiskZone::class.java)?.copy(zoneId = doc.id)
                } ?: emptyList()
                
                trySend(zones)
            }
        
        awaitClose { subscription.remove() }
    }
}
