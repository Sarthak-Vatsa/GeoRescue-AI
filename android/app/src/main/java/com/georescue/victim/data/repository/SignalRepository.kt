package com.georescue.victim.data.repository

import com.georescue.victim.domain.models.SignalType

/**
 * Contract for sending victim distress signals to the backend.
 * The backend (Cloud Function) reads from signals/ and creates the actual incident.
 */
interface SignalRepository {
    /**
     * Writes a new document to the Firestore `signals/` collection.
     * @return Result containing the generated Firestore document ID on success.
     */
    suspend fun sendSignal(type: SignalType): Result<String>
}
