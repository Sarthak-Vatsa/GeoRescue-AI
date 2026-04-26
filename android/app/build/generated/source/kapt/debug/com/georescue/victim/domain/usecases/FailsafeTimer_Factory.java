package com.georescue.victim.domain.usecases;

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
public final class FailsafeTimer_Factory implements Factory<FailsafeTimer> {
  private final Provider<SignalUseCase> signalUseCaseProvider;

  public FailsafeTimer_Factory(Provider<SignalUseCase> signalUseCaseProvider) {
    this.signalUseCaseProvider = signalUseCaseProvider;
  }

  @Override
  public FailsafeTimer get() {
    return newInstance(signalUseCaseProvider.get());
  }

  public static FailsafeTimer_Factory create(Provider<SignalUseCase> signalUseCaseProvider) {
    return new FailsafeTimer_Factory(signalUseCaseProvider);
  }

  public static FailsafeTimer newInstance(SignalUseCase signalUseCase) {
    return new FailsafeTimer(signalUseCase);
  }
}
