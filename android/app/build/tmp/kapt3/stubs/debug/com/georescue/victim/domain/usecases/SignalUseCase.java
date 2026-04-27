package com.georescue.victim.domain.usecases;

/**
 * Use case responsible for triggering a distress signal.
 *
 * Called by:
 * - [FailsafeTimer] when the 30-second countdown expires (auto INACTIVITY)
 * - Future: SOS button in UI (manual SOS)
 *
 * Delegates to [SignalRepository] which writes to Firestore `signals/` collection.
 * The backend Cloud Function then creates the actual `incidents/` document.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u000f"}, d2 = {"Lcom/georescue/victim/domain/usecases/SignalUseCase;", "", "signalRepository", "Lcom/georescue/victim/data/repository/SignalRepository;", "(Lcom/georescue/victim/data/repository/SignalRepository;)V", "invoke", "Lkotlin/Result;", "", "type", "Lcom/georescue/victim/domain/models/SignalType;", "invoke-gIAlu-s", "(Lcom/georescue/victim/domain/models/SignalType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "triggerSOS", "triggerSOS-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class SignalUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.data.repository.SignalRepository signalRepository = null;
    
    @javax.inject.Inject()
    public SignalUseCase(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.data.repository.SignalRepository signalRepository) {
        super();
    }
}