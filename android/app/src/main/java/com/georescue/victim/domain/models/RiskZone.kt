package com.georescue.victim.domain.models

import com.google.firebase.Timestamp

data class RiskZone(
    val zoneId: String = "",
    val name: String = "",
    val type: String = "", // landslide, earthquake
    val center: GeoPoint = GeoPoint(),
    val radius: Double = 0.0,
    val severity: RiskSeverity = RiskSeverity.LOW,
    val updatedAt: Timestamp = Timestamp.now()
)

enum class RiskSeverity {
    LOW, MEDIUM, HIGH
}
