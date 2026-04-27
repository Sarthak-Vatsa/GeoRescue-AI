package com.georescue.victim.data

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Reads the current battery level from the system BatteryManager.
 * Returns an integer percentage (0–100), or -1 if unavailable.
 */
@Singleton
class BatteryReader @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getBatteryLevel(): Int {
        val intent = context.registerReceiver(
            null,
            IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        ) ?: return -1

        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

        if (level == -1 || scale == -1) return -1
        return (level * 100 / scale.toFloat()).toInt()
    }
}
