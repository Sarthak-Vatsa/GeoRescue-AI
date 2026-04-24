package com.georescue.victim.data;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t0\bH\u0002J\u000e\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/georescue/victim/data/LiveStatusStreamer;", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/database/FirebaseDatabase;)V", "getMockLocationFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlin/Pair;", "", "startStreaming", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class LiveStatusStreamer {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.database.FirebaseDatabase database = null;
    
    @javax.inject.Inject()
    public LiveStatusStreamer(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase database) {
        super();
    }
    
    /**
     * Starts streaming location to RTDB.
     * Includes both a mocked approach and an injection approach.
     * The unused one can be commented out by the user.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object startStreaming(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final kotlinx.coroutines.flow.Flow<kotlin.Pair<java.lang.Double, java.lang.Double>> getMockLocationFlow() {
        return null;
    }
}