package com.georescue.victim.domain.models

data class KineticData(
    val x: Float,
    val y: Float,
    val z: Float,
    val magnitude: Float,
    val timestamp: Long = System.currentTimeMillis()
)
