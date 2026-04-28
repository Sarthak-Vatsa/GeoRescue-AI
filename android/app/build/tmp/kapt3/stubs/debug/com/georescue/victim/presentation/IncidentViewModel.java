package com.georescue.victim.presentation;

/**
 * Shared ViewModel for the entire GeoRescue victim UI.
 *
 * Surfaces:
 * - [currentIncident] — live Firestore incident for this user (read-only observer)
 * - [riskZones] — loaded once for map overlays
 * - [failsafeRemainingTime] — countdown seconds, drives the animated ring
 * - [uiState] — derived state that drives NavGraph navigation transitions
 *
 * Action entry points for the UI:
 * - [triggerSOSManually] — called by the "SEND SOS NOW" button
 * - [cancelFailsafe] — called by the "I'M SAFE" button
 * - [ensureObserving] — called when app foregrounds without DetectionService
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%J\b\u0010\'\u001a\u00020%H\u0007J\b\u0010(\u001a\u00020%H\u0002J\b\u0010)\u001a\u00020%H\u0014J\u0006\u0010*\u001a\u00020%R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\u00a8\u0006+"}, d2 = {"Lcom/georescue/victim/presentation/IncidentViewModel;", "Landroidx/lifecycle/ViewModel;", "incidentObserver", "Lcom/georescue/victim/data/repository/IncidentObserver;", "signalUseCase", "Lcom/georescue/victim/domain/usecases/SignalUseCase;", "failsafeTimer", "Lcom/georescue/victim/domain/usecases/FailsafeTimer;", "riskZoneRepository", "Lcom/georescue/victim/data/repository/RiskZoneRepository;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "(Lcom/georescue/victim/data/repository/IncidentObserver;Lcom/georescue/victim/domain/usecases/SignalUseCase;Lcom/georescue/victim/domain/usecases/FailsafeTimer;Lcom/georescue/victim/data/repository/RiskZoneRepository;Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "_riskZones", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/georescue/victim/domain/models/RiskZone;", "_userLocation", "Lcom/google/android/gms/maps/model/LatLng;", "currentIncident", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/georescue/victim/domain/models/Incident;", "getCurrentIncident", "()Lkotlinx/coroutines/flow/StateFlow;", "failsafeRemainingTime", "", "getFailsafeRemainingTime", "isFailsafeActive", "", "riskZones", "getRiskZones", "uiState", "Lcom/georescue/victim/presentation/GeoRescueUiState;", "getUiState", "userLocation", "getUserLocation", "cancelFailsafe", "", "ensureObserving", "fetchLastLocation", "loadRiskZones", "onCleared", "triggerSOSManually", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class IncidentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.IncidentObserver incidentObserver = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.usecases.SignalUseCase signalUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.usecases.FailsafeTimer failsafeTimer = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.RiskZoneRepository riskZoneRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.google.android.gms.maps.model.LatLng> _userLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> userLocation = null;
    
    /**
     * Latest active incident for this user. Null when none exists.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> currentIncident = null;
    
    /**
     * Countdown in seconds (0…30). Drives the circular progress ring.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> failsafeRemainingTime = null;
    
    /**
     * True while the countdown is actively ticking.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isFailsafeActive = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.georescue.victim.domain.models.RiskZone>> _riskZones = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.georescue.victim.domain.models.RiskZone>> riskZones = null;
    
    /**
     * Priority order:
     * 1. Active non-resolved incident → RescueActive
     * 2. Failsafe timer running       → SOSCountdown
     * 3. Otherwise                    → Monitoring
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.presentation.GeoRescueUiState> uiState = null;
    
    @javax.inject.Inject()
    public IncidentViewModel(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.IncidentObserver incidentObserver, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.usecases.SignalUseCase signalUseCase, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.usecases.FailsafeTimer failsafeTimer, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.RiskZoneRepository riskZoneRepository, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.FusedLocationProviderClient fusedLocationClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.google.android.gms.maps.model.LatLng> getUserLocation() {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    public final void fetchLastLocation() {
    }
    
    /**
     * Latest active incident for this user. Null when none exists.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> getCurrentIncident() {
        return null;
    }
    
    /**
     * Countdown in seconds (0…30). Drives the circular progress ring.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getFailsafeRemainingTime() {
        return null;
    }
    
    /**
     * True while the countdown is actively ticking.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isFailsafeActive() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.georescue.victim.domain.models.RiskZone>> getRiskZones() {
        return null;
    }
    
    private final void loadRiskZones() {
    }
    
    /**
     * Priority order:
     * 1. Active non-resolved incident → RescueActive
     * 2. Failsafe timer running       → SOSCountdown
     * 3. Otherwise                    → Monitoring
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.presentation.GeoRescueUiState> getUiState() {
        return null;
    }
    
    /**
     * Starts Firestore incident listener if not already running.
     */
    public final void ensureObserving() {
    }
    
    /**
     * Called by "SEND SOS NOW" button on SOSCountdownScreen.
     */
    public final void triggerSOSManually() {
    }
    
    /**
     * Called by "I'M SAFE" button on SOSCountdownScreen.
     */
    public final void cancelFailsafe() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}