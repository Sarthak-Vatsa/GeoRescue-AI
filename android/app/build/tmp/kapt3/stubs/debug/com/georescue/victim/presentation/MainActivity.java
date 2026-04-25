package com.georescue.victim.presentation;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020\'H\u0002J\u0012\u0010(\u001a\u00020\'2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020\'H\u0002J\b\u0010,\u001a\u00020\'H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000e0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00198\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR+\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u00168B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\u00a8\u0006-"}, d2 = {"Lcom/georescue/victim/presentation/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "authRepository", "Lcom/georescue/victim/data/repository/AuthRepository;", "getAuthRepository", "()Lcom/georescue/victim/data/repository/AuthRepository;", "setAuthRepository", "(Lcom/georescue/victim/data/repository/AuthRepository;)V", "backgroundPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "foregroundPermissionLauncher", "", "geofenceManager", "Lcom/georescue/victim/service/GeofenceManager;", "getGeofenceManager", "()Lcom/georescue/victim/service/GeofenceManager;", "setGeofenceManager", "(Lcom/georescue/victim/service/GeofenceManager;)V", "hasLocationPermission", "", "isAuthenticated", "riskZoneRepository", "Lcom/georescue/victim/data/repository/RiskZoneRepository;", "getRiskZoneRepository", "()Lcom/georescue/victim/data/repository/RiskZoneRepository;", "setRiskZoneRepository", "(Lcom/georescue/victim/data/repository/RiskZoneRepository;)V", "<set-?>", "showBackgroundRationaleDialog", "getShowBackgroundRationaleDialog", "()Z", "setShowBackgroundRationaleDialog", "(Z)V", "showBackgroundRationaleDialog$delegate", "Landroidx/compose/runtime/MutableState;", "maybeStartMonitoring", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "signInAndStartMonitoring", "startMonitoring", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @javax.inject.Inject()
    public com.georescue.victim.data.repository.AuthRepository authRepository;
    @javax.inject.Inject()
    public com.georescue.victim.data.repository.RiskZoneRepository riskZoneRepository;
    @javax.inject.Inject()
    public com.georescue.victim.service.GeofenceManager geofenceManager;
    private boolean isAuthenticated = false;
    private boolean hasLocationPermission = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState showBackgroundRationaleDialog$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> backgroundPermissionLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> foregroundPermissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.data.repository.AuthRepository getAuthRepository() {
        return null;
    }
    
    public final void setAuthRepository(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.AuthRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.data.repository.RiskZoneRepository getRiskZoneRepository() {
        return null;
    }
    
    public final void setRiskZoneRepository(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.RiskZoneRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.service.GeofenceManager getGeofenceManager() {
        return null;
    }
    
    public final void setGeofenceManager(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.service.GeofenceManager p0) {
    }
    
    private final boolean getShowBackgroundRationaleDialog() {
        return false;
    }
    
    private final void setShowBackgroundRationaleDialog(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void signInAndStartMonitoring() {
    }
    
    private final void maybeStartMonitoring() {
    }
    
    private final void startMonitoring() {
    }
}