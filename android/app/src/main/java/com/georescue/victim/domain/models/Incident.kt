package com.georescue.victim.domain.models

import com.google.firebase.Timestamp

data class Incident(
    val incidentId: String = "",
    val type: String = "", // landslide, earthquake
    val triggerType: String = "", // manual, auto
    val status: IncidentStatus = IncidentStatus.CREATED,
    val location: GeoPoint = GeoPoint(),
    val createdAt: Timestamp = Timestamp.now(),
    val reportedBy: String = "",
    val assignedResponderId: String? = null,
    val confidenceScore: Double = 0.0,
    val timeline: IncidentTimeline = IncidentTimeline()
)

enum class IncidentStatus {
    CREATED, ASSIGNED, RESPONDING, RESOLVED
}

data class IncidentTimeline(
    val assignedAt: Timestamp? = null,
    val respondedAt: Timestamp? = null,
    val resolvedAt: Timestamp? = null
)
