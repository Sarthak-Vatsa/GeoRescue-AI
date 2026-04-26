package com.georescue.victim.data.repository;

import android.hardware.SensorManager;
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
public final class SensorRepositoryImpl_Factory implements Factory<SensorRepositoryImpl> {
  private final Provider<SensorManager> sensorManagerProvider;

  public SensorRepositoryImpl_Factory(Provider<SensorManager> sensorManagerProvider) {
    this.sensorManagerProvider = sensorManagerProvider;
  }

  @Override
  public SensorRepositoryImpl get() {
    return newInstance(sensorManagerProvider.get());
  }

  public static SensorRepositoryImpl_Factory create(Provider<SensorManager> sensorManagerProvider) {
    return new SensorRepositoryImpl_Factory(sensorManagerProvider);
  }

  public static SensorRepositoryImpl newInstance(SensorManager sensorManager) {
    return new SensorRepositoryImpl(sensorManager);
  }
}
