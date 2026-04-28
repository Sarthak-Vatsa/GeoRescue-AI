package com.georescue.victim.domain.usecases;

/**
 * 30-second failsafe countdown timer. Fires an INACTIVITY signal when it expires.
 * Marked @Singleton so DetectionService and IncidentViewModel share the same instance
 * and observe the same [remainingTime] / [isActive] flows.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0012R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/georescue/victim/domain/usecases/FailsafeTimer;", "", "signalUseCase", "Lcom/georescue/victim/domain/usecases/SignalUseCase;", "(Lcom/georescue/victim/domain/usecases/SignalUseCase;)V", "_isActive", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_remainingTime", "", "isActive", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "remainingTime", "getRemainingTime", "timerJob", "Lkotlinx/coroutines/Job;", "resetTimer", "", "startTimer", "scope", "Lkotlinx/coroutines/CoroutineScope;", "stopTimer", "Companion", "app_debug"})
public final class FailsafeTimer {
    @org.jetbrains.annotations.NotNull()
    private final com.georescue.victim.domain.usecases.SignalUseCase signalUseCase = null;
    public static final int COUNTDOWN_SECONDS = 30;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _remainingTime = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> remainingTime = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isActive = null;
    
    /**
     * True while the countdown is ticking. Drives SOSCountdown navigation state.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isActive = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job timerJob;
    @org.jetbrains.annotations.NotNull()
    public static final com.georescue.victim.domain.usecases.FailsafeTimer.Companion Companion = null;
    
    @javax.inject.Inject()
    public FailsafeTimer(@org.jetbrains.annotations.NotNull()
    com.georescue.victim.domain.usecases.SignalUseCase signalUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getRemainingTime() {
        return null;
    }
    
    /**
     * True while the countdown is ticking. Drives SOSCountdown navigation state.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isActive() {
        return null;
    }
    
    public final void startTimer(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    /**
     * Called when new movement is detected — resets countdown but keeps timer running.
     */
    public final void resetTimer() {
    }
    
    /**
     * Called when the user taps "I'm Safe" — cancels entirely.
     */
    public final void stopTimer() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/georescue/victim/domain/usecases/FailsafeTimer$Companion;", "", "()V", "COUNTDOWN_SECONDS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}