package com.georescue.victim.domain.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\nH\u00c6\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006!"}, d2 = {"Lcom/georescue/victim/domain/models/Signal;", "", "signalId", "", "userId", "type", "Lcom/georescue/victim/domain/models/SignalType;", "location", "Lcom/georescue/victim/domain/models/GeoPoint;", "timestamp", "Lcom/google/firebase/Timestamp;", "(Ljava/lang/String;Ljava/lang/String;Lcom/georescue/victim/domain/models/SignalType;Lcom/georescue/victim/domain/models/GeoPoint;Lcom/google/firebase/Timestamp;)V", "getLocation", "()Lcom/georescue/victim/domain/models/GeoPoint;", "getSignalId", "()Ljava/lang/String;", "getTimestamp", "()Lcom/google/firebase/Timestamp;", "getType", "()Lcom/georescue/victim/domain/models/SignalType;", "getUserId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class Signal {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String signalId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.models.SignalType type = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.models.GeoPoint location = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.Timestamp timestamp = null;
    
    public Signal(@org.jetbrains.annotations.NotNull()
    java.lang.String signalId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.SignalType type, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.GeoPoint location, @org.jetbrains.annotations.NotNull()
    com.google.firebase.Timestamp timestamp) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSignalId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.SignalType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.GeoPoint getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.Timestamp getTimestamp() {
        return null;
    }
    
    public Signal() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.SignalType component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.GeoPoint component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.Timestamp component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.Signal copy(@org.jetbrains.annotations.NotNull()
    java.lang.String signalId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.SignalType type, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.GeoPoint location, @org.jetbrains.annotations.NotNull()
    com.google.firebase.Timestamp timestamp) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}