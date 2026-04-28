package com.georescue.victim.domain.usecases;

import com.georescue.victim.data.repository.IncidentObserver;
import com.georescue.victim.data.repository.SignalRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class SignalUseCase_Factory implements Factory<SignalUseCase> {
  private final Provider<SignalRepository> signalRepositoryProvider;

  private final Provider<IncidentObserver> incidentObserverProvider;

  public SignalUseCase_Factory(Provider<SignalRepository> signalRepositoryProvider,
      Provider<IncidentObserver> incidentObserverProvider) {
    this.signalRepositoryProvider = signalRepositoryProvider;
    this.incidentObserverProvider = incidentObserverProvider;
  }

  @Override
  public SignalUseCase get() {
    return newInstance(signalRepositoryProvider.get(), incidentObserverProvider.get());
  }

  public static SignalUseCase_Factory create(Provider<SignalRepository> signalRepositoryProvider,
      Provider<IncidentObserver> incidentObserverProvider) {
    return new SignalUseCase_Factory(signalRepositoryProvider, incidentObserverProvider);
  }

  public static SignalUseCase newInstance(SignalRepository signalRepository,
      IncidentObserver incidentObserver) {
    return new SignalUseCase(signalRepository, incidentObserver);
  }
}
