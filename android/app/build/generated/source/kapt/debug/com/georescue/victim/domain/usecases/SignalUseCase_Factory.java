package com.georescue.victim.domain.usecases;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class SignalUseCase_Factory implements Factory<SignalUseCase> {
  @Override
  public SignalUseCase get() {
    return newInstance();
  }

  public static SignalUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SignalUseCase newInstance() {
    return new SignalUseCase();
  }

  private static final class InstanceHolder {
    private static final SignalUseCase_Factory INSTANCE = new SignalUseCase_Factory();
  }
}
