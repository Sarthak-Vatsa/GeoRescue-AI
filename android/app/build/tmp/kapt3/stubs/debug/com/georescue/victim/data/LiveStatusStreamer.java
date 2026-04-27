package com.georescue.victim.data;

/**
 * Streams real device telemetry (location + battery) to RTDB `locations/{userId}`
 * every [STREAM_INTERVAL_MS] milliseconds while the [DetectionService] is running.
 *
 * RTDB schema (matches architecture.md §3):
 * {
 *  "lat":       number,
 *  "lng":       number,
 *  "battery":   number,
 *  "updatedAt": ServerValue.TIMESTAMP
 * }
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001a\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00130\u0012H\u0003J\u000e\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0019"}, d2 = {"Lcom/georescue/victim/data/LiveStatusStreamer;", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "fusedLocation", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "batteryReader", "Lcom/georescue/victim/data/BatteryReader;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/database/FirebaseDatabase;Lcom/google/android/gms/location/FusedLocationProviderClient;Lcom/georescue/victim/data/BatteryReader;)V", "signalRepository", "Lcom/georescue/victim/data/repository/SignalRepository;", "getSignalRepository", "()Lcom/georescue/victim/data/repository/SignalRepository;", "setSignalRepository", "(Lcom/georescue/victim/data/repository/SignalRepository;)V", "getLocationFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Pair;", "", "startStreaming", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class LiveStatusStreamer {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.gms.location.FusedLocationProviderClient fusedLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.BatteryReader batteryReader = null;
    private static final long STREAM_INTERVAL_MS = 15000L;
    private static final long LOCATION_INTERVAL_MS = 10000L;
    
    /**
     * Starts the real-time telemetry loop.
     * Suspends until the calling coroutine is cancelled (i.e., service destroyed).
     */
    @javax.inject.Inject()
    public com.georescue.victim.data.repository.SignalRepository signalRepository;
    @org.jetbrains.annotations.NotNull()
    public static final com.georescue.victim.data.LiveStatusStreamer.Companion Companion = null;
    
    @javax.inject.Inject()
    public LiveStatusStreamer(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.FusedLocationProviderClient fusedLocation, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.BatteryReader batteryReader) {
        super();
    }
    
    /**
     * Starts the real-time telemetry loop.
     * Suspends until the calling coroutine is cancelled (i.e., service destroyed).
     */
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.data.repository.SignalRepository getSignalRepository() {
        return null;
    }
    
    /**
     * Starts the real-time telemetry loop.
     * Suspends until the calling coroutine is cancelled (i.e., service destroyed).
     */
    public final void setSignalRepository(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.SignalRepository p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object startStreaming(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Produces a continuous [Flow] of (lat, lng) pairs from [FusedLocationProviderClient].
     * Uses [callbackFlow] to bridge the GMS callback API into coroutines.
     */
    @android.annotation.SuppressLint(value = {"MissingPermission"})
    private final kotlinx.coroutines.flow.Flow<kotlin.Pair<java.lang.Double, java.lang.Double>> getLocationFlow() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/georescue/victim/data/LiveStatusStreamer$Companion;", "", "()V", "LOCATION_INTERVAL_MS", "", "STREAM_INTERVAL_MS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}