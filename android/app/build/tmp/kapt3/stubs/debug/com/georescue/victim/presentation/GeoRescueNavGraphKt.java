package com.georescue.victim.presentation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006\u0006"}, d2 = {"GeoRescueNavGraph", "", "viewModel", "Lcom/georescue/victim/presentation/IncidentViewModel;", "onStartMonitoring", "Lkotlin/Function0;", "app_debug"})
public final class GeoRescueNavGraphKt {
    
    /**
     * Root navigation graph.
     *
     * Navigation is driven reactively by [IncidentViewModel.uiState]:
     * - [GeoRescueUiState.Monitoring]   → stays on / returns to "monitoring"
     * - [GeoRescueUiState.SOSCountdown] → pushes "sos_countdown"
     * - [GeoRescueUiState.RescueActive] → pushes "rescue_tracking"
     *
     * The LaunchedEffect lives inside MonitoringScreen so it only fires when the
     * user is actively on the home screen (not during the countdown or rescue views).
     */
    @androidx.compose.runtime.Composable()
    public static final void GeoRescueNavGraph(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.presentation.IncidentViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onStartMonitoring) {
    }
}