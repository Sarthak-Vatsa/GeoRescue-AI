package com.georescue.victim.presentation;

/**
 * ViewModel that surfaces the current incident state to the UI layer.
 *
 * Wraps [IncidentObserver] (a Singleton) so the UI reacts to real-time
 * Firestore updates whenever the incident status changes:
 *  CREATED → ASSIGNED → RESPONDING → RESOLVED
 *
 * The observer is started/stopped by [DetectionService] for background safety.
 * This ViewModel only reads the shared [StateFlow] — it does not own the listener lifecycle.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\u000bH\u0014R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/georescue/victim/presentation/IncidentViewModel;", "Landroidx/lifecycle/ViewModel;", "incidentObserver", "Lcom/georescue/victim/data/repository/IncidentObserver;", "(Lcom/georescue/victim/data/repository/IncidentObserver;)V", "currentIncident", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/georescue/victim/domain/models/Incident;", "getCurrentIncident", "()Lkotlinx/coroutines/flow/StateFlow;", "ensureObserving", "", "onCleared", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class IncidentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.IncidentObserver incidentObserver = null;
    
    /**
     * Emits the latest incident for the current user, or null if none exists.
     * Compose screens collect this to drive the RescueStatusCard and Safety Banner.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> currentIncident = null;
    
    @javax.inject.Inject()
    public IncidentViewModel(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.IncidentObserver incidentObserver) {
        super();
    }
    
    /**
     * Emits the latest incident for the current user, or null if none exists.
     * Compose screens collect this to drive the RescueStatusCard and Safety Banner.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> getCurrentIncident() {
        return null;
    }
    
    /**
     * Called when the app is in the foreground without DetectionService running
     * (e.g., user opens the app manually). Starts observing if not already started.
     */
    public final void ensureObserving() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}