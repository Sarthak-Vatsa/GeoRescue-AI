package com.georescue.victim.service;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 42\u00020\u0001:\u00014B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010#\u001a\u00020$H\u0002J\u0014\u0010%\u001a\u0004\u0018\u00010&2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020$H\u0016J\"\u0010*\u001a\u00020+2\b\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+H\u0016J\b\u0010.\u001a\u00020$H\u0002J\b\u0010/\u001a\u00020$H\u0002J\b\u00100\u001a\u00020$H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u00102\u001a\u000203H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001b\u001a\u00020\u001c8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/georescue/victim/service/DetectionService;", "Landroid/app/Service;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "getAuth", "()Lcom/google/firebase/auth/FirebaseAuth;", "setAuth", "(Lcom/google/firebase/auth/FirebaseAuth;)V", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "getDatabase", "()Lcom/google/firebase/database/FirebaseDatabase;", "setDatabase", "(Lcom/google/firebase/database/FirebaseDatabase;)V", "failsafeTimer", "Lcom/georescue/victim/domain/usecases/FailsafeTimer;", "getFailsafeTimer", "()Lcom/georescue/victim/domain/usecases/FailsafeTimer;", "setFailsafeTimer", "(Lcom/georescue/victim/domain/usecases/FailsafeTimer;)V", "inactivityUseCase", "Lcom/georescue/victim/domain/usecases/InactivityUseCase;", "getInactivityUseCase", "()Lcom/georescue/victim/domain/usecases/InactivityUseCase;", "setInactivityUseCase", "(Lcom/georescue/victim/domain/usecases/InactivityUseCase;)V", "liveStatusStreamer", "Lcom/georescue/victim/data/LiveStatusStreamer;", "getLiveStatusStreamer", "()Lcom/georescue/victim/data/LiveStatusStreamer;", "setLiveStatusStreamer", "(Lcom/georescue/victim/data/LiveStatusStreamer;)V", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onStartCommand", "", "flags", "startId", "setupRTDBPresence", "startDetectionPipelines", "startForegroundService", "writePresence", "userId", "", "Companion", "app_debug"})
public final class DetectionService extends android.app.Service {
    @javax.inject.Inject()
    public com.google.firebase.auth.FirebaseAuth auth;
    @javax.inject.Inject()
    public com.google.firebase.database.FirebaseDatabase database;
    @javax.inject.Inject()
    public com.georescue.victim.data.LiveStatusStreamer liveStatusStreamer;
    @javax.inject.Inject()
    public com.georescue.victim.domain.usecases.InactivityUseCase inactivityUseCase;
    @javax.inject.Inject()
    public com.georescue.victim.domain.usecases.FailsafeTimer failsafeTimer;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "ACTION_START";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "ACTION_STOP";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "risk_detection_channel";
    private static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.georescue.victim.service.DetectionService.Companion Companion = null;
    
    public DetectionService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth getAuth() {
        return null;
    }
    
    public final void setAuth(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.database.FirebaseDatabase getDatabase() {
        return null;
    }
    
    public final void setDatabase(@org.jetbrains.annotations.NotNull()
    com.google.firebase.database.FirebaseDatabase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.data.LiveStatusStreamer getLiveStatusStreamer() {
        return null;
    }
    
    public final void setLiveStatusStreamer(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.LiveStatusStreamer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.usecases.InactivityUseCase getInactivityUseCase() {
        return null;
    }
    
    public final void setInactivityUseCase(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.usecases.InactivityUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.usecases.FailsafeTimer getFailsafeTimer() {
        return null;
    }
    
    public final void setFailsafeTimer(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.usecases.FailsafeTimer p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void startForegroundService() {
    }
    
    private final void setupRTDBPresence() {
    }
    
    private final void writePresence(java.lang.String userId) {
    }
    
    private final void startDetectionPipelines() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void createNotificationChannel() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/georescue/victim/service/DetectionService$Companion;", "", "()V", "ACTION_START", "", "ACTION_STOP", "CHANNEL_ID", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}