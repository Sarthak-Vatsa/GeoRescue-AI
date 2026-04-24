package com.georescue.victim.domain.models

import com.google.firebase.Timestamp

data class Signal(
    val signalId: String = "",
    val userId: String = "",
    val type: SignalType = SignalType.SOS,
    val location: GeoPoint = GeoPoint(),
    val timestamp: Timestamp = Timestamp.now()
)

enum class SignalType {
    SOS, INACTIVITY
}

data class GeoPoint(
    val lat: Double = 0.0,
    val lng: Double = 0.0
)
