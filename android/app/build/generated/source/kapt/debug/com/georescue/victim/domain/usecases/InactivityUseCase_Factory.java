package com.georescue.victim.domain.usecases;

import com.georescue.victim.data.repository.SensorRepository;
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
public final class InactivityUseCase_Factory implements Factory<InactivityUseCase> {
  private final Provider<SensorRepository> sensorRepositoryProvider;

  public InactivityUseCase_Factory(Provider<SensorRepository> sensorRepositoryProvider) {
    this.sensorRepositoryProvider = sensorRepositoryProvider;
  }

  @Override
  public InactivityUseCase get() {
    return newInstance(sensorRepositoryProvider.get());
  }

  public static InactivityUseCase_Factory create(
      Provider<SensorRepository> sensorRepositoryProvider) {
    return new InactivityUseCase_Factory(sensorRepositoryProvider);
  }

  public static InactivityUseCase newInstance(SensorRepository sensorRepository) {
    return new InactivityUseCase(sensorRepository);
  }
}
