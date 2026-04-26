package com.georescue.victim.di;

import android.hardware.SensorManager;
import com.georescue.victim.data.repository.SensorRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class SensorModule_ProvideSensorRepositoryFactory implements Factory<SensorRepository> {
  private final Provider<SensorManager> sensorManagerProvider;

  public SensorModule_ProvideSensorRepositoryFactory(
      Provider<SensorManager> sensorManagerProvider) {
    this.sensorManagerProvider = sensorManagerProvider;
  }

  @Override
  public SensorRepository get() {
    return provideSensorRepository(sensorManagerProvider.get());
  }

  public static SensorModule_ProvideSensorRepositoryFactory create(
      Provider<SensorManager> sensorManagerProvider) {
    return new SensorModule_ProvideSensorRepositoryFactory(sensorManagerProvider);
  }

  public static SensorRepository provideSensorRepository(SensorManager sensorManager) {
    return Preconditions.checkNotNullFromProvides(SensorModule.INSTANCE.provideSensorRepository(sensorManager));
  }
}
