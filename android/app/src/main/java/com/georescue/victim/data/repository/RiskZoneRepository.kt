package com.georescue.victim.data.repository

import android.util.Log
import com.georescue.victim.domain.models.RiskSeverity
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

//                val zones = snapshot?.documents?.mapNotNull { doc ->
//                    try {
//                        val rawSeverity = doc.get("severity")
//                        Log.d("DEBUG_RAW", "doc=${doc.id}, severity=[$rawSeverity]")
//
//                        // comment out this line for now
//                        //val severity = RiskSeverity.valueOf(rawSeverity.toString())
//                        //Log.d("DEBUG_SEVERITY", "doc=${doc.id}, severity=$severity")
//                        doc.toObject(RiskZone::class.java)
//
//                        null
//                    } catch (e: Exception) {
//                        Log.e("DEBUG_ERROR", "doc=${doc.id}", e)
//                        null
//                    }
//                } ?: emptyList()
                
                trySend(zones)
            }
        
        awaitClose { subscription.remove() }
    }
}
