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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001a\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r0\fH\u0003J\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/georescue/victim/data/LiveStatusStreamer;", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "fusedLocation", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "batteryReader", "Lcom/georescue/victim/data/BatteryReader;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/database/FirebaseDatabase;Lcom/google/android/gms/location/FusedLocationProviderClient;Lcom/georescue/victim/data/BatteryReader;)V", "getLocationFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Pair;", "", "startStreaming", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
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