package com.georescue.victim.presentation.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006\u0007"}, d2 = {"SOSCountdownScreen", "", "viewModel", "Lcom/georescue/victim/presentation/IncidentViewModel;", "onSafe", "Lkotlin/Function0;", "onSOSSent", "app_debug"})
public final class SOSCountdownScreenKt {
    
    /**
     * SOS Countdown screen — shown when inactivity is detected or when the user
     * manually initiates an SOS flow.
     *
     * Features:
     * - Animated circular countdown ring driven by [FailsafeTimer.remainingTime]
     * - Intensifying haptic feedback (light → medium → heavy) as timer nears zero
     * - "I'M SAFE" cancels the timer and returns home
     * - "SEND SOS NOW" triggers signal immediately and waits for reactive navigation
     *   to [RescueTrackingScreen] via [GeoRescueUiState.RescueActive]
     */
    @androidx.compose.runtime.Composable()
    public static final void SOSCountdownScreen(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.presentation.IncidentViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSafe, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSOSSent) {
    }
}