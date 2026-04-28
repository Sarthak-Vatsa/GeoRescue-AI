package com.georescue.victim.presentation;

import com.georescue.victim.data.repository.IncidentObserver;
import com.georescue.victim.data.repository.RiskZoneRepository;
import com.georescue.victim.domain.usecases.FailsafeTimer;
import com.georescue.victim.domain.usecases.SignalUseCase;
import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class IncidentViewModel_Factory implements Factory<IncidentViewModel> {
  private final Provider<IncidentObserver> incidentObserverProvider;

  private final Provider<SignalUseCase> signalUseCaseProvider;

  private final Provider<FailsafeTimer> failsafeTimerProvider;

  private final Provider<RiskZoneRepository> riskZoneRepositoryProvider;

  private final Provider<FusedLocationProviderClient> fusedLocationClientProvider;

  public IncidentViewModel_Factory(Provider<IncidentObserver> incidentObserverProvider,
      Provider<SignalUseCase> signalUseCaseProvider, Provider<FailsafeTimer> failsafeTimerProvider,
      Provider<RiskZoneRepository> riskZoneRepositoryProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider) {
    this.incidentObserverProvider = incidentObserverProvider;
    this.signalUseCaseProvider = signalUseCaseProvider;
    this.failsafeTimerProvider = failsafeTimerProvider;
    this.riskZoneRepositoryProvider = riskZoneRepositoryProvider;
    this.fusedLocationClientProvider = fusedLocationClientProvider;
  }

  @Override
  public IncidentViewModel get() {
    return newInstance(incidentObserverProvider.get(), signalUseCaseProvider.get(), failsafeTimerProvider.get(), riskZoneRepositoryProvider.get(), fusedLocationClientProvider.get());
  }

  public static IncidentViewModel_Factory create(
      Provider<IncidentObserver> incidentObserverProvider,
      Provider<SignalUseCase> signalUseCaseProvider, Provider<FailsafeTimer> failsafeTimerProvider,
      Provider<RiskZoneRepository> riskZoneRepositoryProvider,
      Provider<FusedLocationProviderClient> fusedLocationClientProvider) {
    return new IncidentViewModel_Factory(incidentObserverProvider, signalUseCaseProvider, failsafeTimerProvider, riskZoneRepositoryProvider, fusedLocationClientProvider);
  }

  public static IncidentViewModel newInstance(IncidentObserver incidentObserver,
      SignalUseCase signalUseCase, FailsafeTimer failsafeTimer,
      RiskZoneRepository riskZoneRepository, FusedLocationProviderClient fusedLocationClient) {
    return new IncidentViewModel(incidentObserver, signalUseCase, failsafeTimer, riskZoneRepository, fusedLocationClient);
  }
}
