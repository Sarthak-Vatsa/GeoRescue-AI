package com.georescue.victim.presentation;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001cH\u0014J\b\u0010 \u001a\u00020\u001cH\u0002J\b\u0010!\u001a\u00020\u001cH\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\""}, d2 = {"Lcom/georescue/victim/presentation/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "authRepository", "Lcom/georescue/victim/data/repository/AuthRepository;", "getAuthRepository", "()Lcom/georescue/victim/data/repository/AuthRepository;", "setAuthRepository", "(Lcom/georescue/victim/data/repository/AuthRepository;)V", "geofenceManager", "Lcom/georescue/victim/service/GeofenceManager;", "getGeofenceManager", "()Lcom/georescue/victim/service/GeofenceManager;", "setGeofenceManager", "(Lcom/georescue/victim/service/GeofenceManager;)V", "riskZoneRepository", "Lcom/georescue/victim/data/repository/RiskZoneRepository;", "getRiskZoneRepository", "()Lcom/georescue/victim/data/repository/RiskZoneRepository;", "setRiskZoneRepository", "(Lcom/georescue/victim/data/repository/RiskZoneRepository;)V", "viewModel", "Lcom/georescue/victim/presentation/IncidentViewModel;", "getViewModel", "()Lcom/georescue/victim/presentation/IncidentViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "signInAndStartMonitoring", "startMonitoring", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @javax.inject.Inject()
    public com.georescue.victim.data.repository.AuthRepository authRepository;
    @javax.inject.Inject()
    public com.georescue.victim.data.repository.RiskZoneRepository riskZoneRepository;
    @javax.inject.Inject()
    public com.georescue.victim.service.GeofenceManager geofenceManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
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
    
    private final com.georescue.victim.presentation.IncidentViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final void signInAndStartMonitoring() {
    }
    
    private final void startMonitoring() {
    }
}