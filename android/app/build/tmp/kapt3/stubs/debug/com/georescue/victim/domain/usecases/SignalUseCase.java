package com.georescue.victim.domain.usecases;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0013"}, d2 = {"Lcom/georescue/victim/domain/usecases/SignalUseCase;", "", "signalRepository", "Lcom/georescue/victim/data/repository/SignalRepository;", "incidentObserver", "Lcom/georescue/victim/data/repository/IncidentObserver;", "(Lcom/georescue/victim/data/repository/SignalRepository;Lcom/georescue/victim/data/repository/IncidentObserver;)V", "lastSignalTime", "", "invoke", "Lkotlin/Result;", "", "type", "Lcom/georescue/victim/domain/models/SignalType;", "invoke-gIAlu-s", "(Lcom/georescue/victim/domain/models/SignalType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerSOS", "triggerSOS-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SignalUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.SignalRepository signalRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.IncidentObserver incidentObserver = null;
    private long lastSignalTime = 0L;
    
    @javax.inject.Inject()
    public SignalUseCase(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.SignalRepository signalRepository, @org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.IncidentObserver incidentObserver) {
        super();
    }
}