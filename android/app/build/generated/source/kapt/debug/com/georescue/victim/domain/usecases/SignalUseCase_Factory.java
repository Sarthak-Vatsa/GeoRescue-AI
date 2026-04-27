package com.georescue.victim.domain.usecases;

import com.georescue.victim.data.repository.SignalRepository;
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
public final class SignalUseCase_Factory implements Factory<SignalUseCase> {
  private final Provider<SignalRepository> signalRepositoryProvider;

  public SignalUseCase_Factory(Provider<SignalRepository> signalRepositoryProvider) {
    this.signalRepositoryProvider = signalRepositoryProvider;
  }

  @Override
  public SignalUseCase get() {
    return newInstance(signalRepositoryProvider.get());
  }

  public static SignalUseCase_Factory create(Provider<SignalRepository> signalRepositoryProvider) {
    return new SignalUseCase_Factory(signalRepositoryProvider);
  }

  public static SignalUseCase newInstance(SignalRepository signalRepository) {
    return new SignalUseCase(signalRepository);
  }
}
