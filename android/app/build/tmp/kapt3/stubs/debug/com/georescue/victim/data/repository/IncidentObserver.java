package com.georescue.victim.data.repository;

/**
 * Attaches a real-time Firestore snapshot listener on the `incidents` collection,
 * filtered to documents where `reportedBy == currentUserId`.
 *
 * The app is READ-ONLY on incidents — state transitions (CREATED → ASSIGNED → RESPONDING → RESOLVED)
 * are performed exclusively by the backend. This observer surfaces those changes to the UI.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0015R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/georescue/victim/data/repository/IncidentObserver;", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "_currentIncident", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/georescue/victim/domain/models/Incident;", "currentIncident", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentIncident", "()Lkotlinx/coroutines/flow/StateFlow;", "listenerRegistration", "Lcom/google/firebase/firestore/ListenerRegistration;", "parseStatus", "Lcom/georescue/victim/domain/models/IncidentStatus;", "raw", "", "startObserving", "", "stopObserving", "app_debug"})
public final class IncidentObserver {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.georescue.victim.domain.models.Incident> _currentIncident = null;
    
    /**
     * Latest active incident for the authenticated user. Null if none exists.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> currentIncident = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.firebase.firestore.ListenerRegistration listenerRegistration;
    
    @javax.inject.Inject()
    public IncidentObserver(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
    }
    
    /**
     * Latest active incident for the authenticated user. Null if none exists.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.georescue.victim.domain.models.Incident> getCurrentIncident() {
        return null;
    }
    
    /**
     * Attaches the Firestore snapshot listener.
     * Safe to call multiple times — will not duplicate listeners.
     */
    public final void startObserving() {
    }
    
    /**
     * Detaches the Firestore snapshot listener and resets state.
     * Call this when the service/ViewModel is destroyed to avoid memory leaks.
     */
    public final void stopObserving() {
    }
    
    private final com.georescue.victim.domain.models.IncidentStatus parseStatus(java.lang.String raw) {
        return null;
    }
}