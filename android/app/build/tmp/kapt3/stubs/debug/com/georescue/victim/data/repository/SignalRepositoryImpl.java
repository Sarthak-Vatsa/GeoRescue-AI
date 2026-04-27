package com.georescue.victim.data.repository;

/**
 * Writes victim distress signals to Firestore `signals/` collection.
 *
 * Schema (matches architecture.md §3 exactly):
 * {
 *  "userId":    string,
 *  "type":      "SOS" | "INACTIVITY",
 *  "location":  { "lat": string, "lng": string },
 *  "timestamp": timestamp (server-side)
 * }
 *
 * The backend Cloud Function watches this collection and creates
 * the actual `incidents/` document — the client never writes incidents directly.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0097@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0010"}, d2 = {"Lcom/georescue/victim/data/repository/SignalRepositoryImpl;", "Lcom/georescue/victim/data/repository/SignalRepository;", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "fusedLocation", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/android/gms/location/FusedLocationProviderClient;)V", "sendSignal", "Lkotlin/Result;", "", "type", "Lcom/georescue/victim/domain/models/SignalType;", "sendSignal-gIAlu-s", "(Lcom/georescue/victim/domain/models/SignalType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SignalRepositoryImpl implements com.georescue.victim.data.repository.SignalRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fusedLocation = null;
    
    @javax.inject.Inject()
    public SignalRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.FusedLocationProviderClient fusedLocation) {
        super();
    }
}