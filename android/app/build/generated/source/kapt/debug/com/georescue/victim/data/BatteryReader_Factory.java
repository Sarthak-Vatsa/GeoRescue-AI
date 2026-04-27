package com.georescue.victim.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class BatteryReader_Factory implements Factory<BatteryReader> {
  private final Provider<Context> contextProvider;

  public BatteryReader_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BatteryReader get() {
    return newInstance(contextProvider.get());
  }

  public static BatteryReader_Factory create(Provider<Context> contextProvider) {
    return new BatteryReader_Factory(contextProvider);
  }

  public static BatteryReader newInstance(Context context) {
    return new BatteryReader(context);
  }
}
