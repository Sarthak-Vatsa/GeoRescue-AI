package com.georescue.victim.presentation;

import com.georescue.victim.data.repository.IncidentObserver;
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

  public IncidentViewModel_Factory(Provider<IncidentObserver> incidentObserverProvider) {
    this.incidentObserverProvider = incidentObserverProvider;
  }

  @Override
  public IncidentViewModel get() {
    return newInstance(incidentObserverProvider.get());
  }

  public static IncidentViewModel_Factory create(
      Provider<IncidentObserver> incidentObserverProvider) {
    return new IncidentViewModel_Factory(incidentObserverProvider);
  }

  public static IncidentViewModel newInstance(IncidentObserver incidentObserver) {
    return new IncidentViewModel(incidentObserver);
  }
}
