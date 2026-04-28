package com.georescue.victim.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.georescue.victim.presentation.screens.MonitoringScreen
import com.georescue.victim.presentation.screens.PermissionScreen
import com.georescue.victim.presentation.screens.SOSCountdownScreen

private object Routes {
    const val PERMISSIONS  = "permissions"
    const val MONITORING   = "monitoring"
    const val SOS_COUNTDOWN = "sos_countdown"
}

/**
 * Root navigation graph.
 *
 * Navigation is driven reactively by [IncidentViewModel.uiState]:
 *  - [GeoRescueUiState.Monitoring]   → stays on / returns to "monitoring"
 *  - [GeoRescueUiState.SOSCountdown] → pushes "sos_countdown"
 *  - [GeoRescueUiState.RescueActive] → pushes "rescue_tracking"
 *
 * The LaunchedEffect lives inside MonitoringScreen so it only fires when the
 * user is actively on the home screen (not during the countdown or rescue views).
 */
@Composable
fun GeoRescueNavGraph(
    viewModel: IncidentViewModel,
    onStartMonitoring: () -> Unit
) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // ── Global state → navigation bridge ────────────────────────────────────
    // Fires whenever uiState changes regardless of current screen
    LaunchedEffect(uiState) {
        when (uiState) {
            is GeoRescueUiState.SOSCountdown -> {
                navController.navigate(Routes.SOS_COUNTDOWN) {
                    launchSingleTop = true
                }
            }
            is GeoRescueUiState.Monitoring, is GeoRescueUiState.RescueActive -> {
                // Both Monitoring and RescueActive are handled by the MONITORING route
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                if (currentRoute == Routes.SOS_COUNTDOWN) {
                    navController.popBackStack(Routes.MONITORING, inclusive = false)
                }
            }
        }
    }

    NavHost(
        navController     = navController,
        startDestination  = Routes.PERMISSIONS
    ) {

        // ── Permission Gate ──────────────────────────────────────────────────
        composable(Routes.PERMISSIONS) {
            PermissionScreen(
                onPermissionsGranted = {
                    onStartMonitoring()
                    navController.navigate(Routes.MONITORING) {
                        popUpTo(Routes.PERMISSIONS) { inclusive = true }
                    }
                }
            )
        }

        // ── Monitoring & Rescue Tracking (Unified Home) ─────────────────────
        composable(Routes.MONITORING) {
            MonitoringScreen(
                viewModel = viewModel,
                onSOSClick = {
                    navController.navigate(Routes.SOS_COUNTDOWN) {
                        launchSingleTop = true
                    }
                }
            )
        }

        // ── SOS Countdown ────────────────────────────────────────────────────
        composable(Routes.SOS_COUNTDOWN) {
            SOSCountdownScreen(
                viewModel = viewModel,
                onSafe = {
                    viewModel.cancelFailsafe()
                    navController.popBackStack(Routes.MONITORING, inclusive = false)
                },
                onSOSSent = {
                    viewModel.triggerSOSManually()
                }
            )
        }
    }
}
