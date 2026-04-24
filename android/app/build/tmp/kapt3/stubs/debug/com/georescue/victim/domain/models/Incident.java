package com.georescue.victim.domain.models;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0011H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0007H\u00c6\u0003J\t\u0010(\u001a\u00020\tH\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u000fH\u00c6\u0003Jo\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u000202H\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014\u00a8\u00064"}, d2 = {"Lcom/georescue/victim/domain/models/Incident;", "", "incidentId", "", "type", "triggerType", "status", "Lcom/georescue/victim/domain/models/IncidentStatus;", "location", "Lcom/georescue/victim/domain/models/GeoPoint;", "createdAt", "Lcom/google/firebase/Timestamp;", "reportedBy", "assignedResponderId", "confidenceScore", "", "timeline", "Lcom/georescue/victim/domain/models/IncidentTimeline;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/georescue/victim/domain/models/IncidentStatus;Lcom/georescue/victim/domain/models/GeoPoint;Lcom/google/firebase/Timestamp;Ljava/lang/String;Ljava/lang/String;DLcom/georescue/victim/domain/models/IncidentTimeline;)V", "getAssignedResponderId", "()Ljava/lang/String;", "getConfidenceScore", "()D", "getCreatedAt", "()Lcom/google/firebase/Timestamp;", "getIncidentId", "getLocation", "()Lcom/georescue/victim/domain/models/GeoPoint;", "getReportedBy", "getStatus", "()Lcom/georescue/victim/domain/models/IncidentStatus;", "getTimeline", "()Lcom/georescue/victim/domain/models/IncidentTimeline;", "getTriggerType", "getType", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class Incident {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String incidentId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String triggerType = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.models.IncidentStatus status = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.models.GeoPoint location = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.Timestamp createdAt = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String reportedBy = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String assignedResponderId = null;
    private final double confidenceScore = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.models.IncidentTimeline timeline = null;
    
    public Incident(@org.jetbrains.annotations.NotNull()
    java.lang.String incidentId, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String triggerType, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.IncidentStatus status, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.GeoPoint location, @org.jetbrains.annotations.NotNull()
    com.google.firebase.Timestamp createdAt, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedBy, @org.jetbrains.annotations.Nullable()
    java.lang.String assignedResponderId, double confidenceScore, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.IncidentTimeline timeline) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIncidentId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTriggerType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.IncidentStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.GeoPoint getLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.Timestamp getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReportedBy() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAssignedResponderId() {
        return null;
    }
    
    public final double getConfidenceScore() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.IncidentTimeline getTimeline() {
        return null;
    }
    
    public Incident() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.IncidentTimeline component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.IncidentStatus component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.GeoPoint component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.Timestamp component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.georescue.victim.domain.models.Incident copy(@org.jetbrains.annotations.NotNull()
    java.lang.String incidentId, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String triggerType, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.IncidentStatus status, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.GeoPoint location, @org.jetbrains.annotations.NotNull()
    com.google.firebase.Timestamp createdAt, @org.jetbrains.annotations.NotNull()
    java.lang.String reportedBy, @org.jetbrains.annotations.Nullable()
    java.lang.String assignedResponderId, double confidenceScore, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.models.IncidentTimeline timeline) {
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