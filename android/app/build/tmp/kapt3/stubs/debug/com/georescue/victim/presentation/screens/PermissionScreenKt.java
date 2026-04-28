package com.georescue.victim.presentation.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00a8\u0006\u0004"}, d2 = {"PermissionScreen", "", "onPermissionsGranted", "Lkotlin/Function0;", "app_debug"})
public final class PermissionScreenKt {
    
    /**
     * Permission gate screen — shown on first launch.
     * Matches the Stitch dark template style with a branded header and permission request flow.
     *
     * Handles:
     * - Foreground location + notification permissions
     * - Background location rationale dialog (Android 10+)
     * - Gracefully calls [onPermissionsGranted] regardless of background location decision
     *   (the app degrades to manual-only monitoring without background geofencing).
     */
    @androidx.compose.runtime.Composable()
    public static final void PermissionScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPermissionsGranted) {
    }
}