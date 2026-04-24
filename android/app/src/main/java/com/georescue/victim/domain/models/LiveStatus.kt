package com.georescue.victim.domain.models

import com.google.firebase.Timestamp

data class LiveStatus(
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val battery: Int = 0,
    val updatedAt: Timestamp = Timestamp.now()
)
