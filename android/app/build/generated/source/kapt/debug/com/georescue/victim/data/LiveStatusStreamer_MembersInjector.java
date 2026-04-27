package com.georescue.victim.data;

import com.georescue.victim.data.repository.SignalRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class LiveStatusStreamer_MembersInjector implements MembersInjector<LiveStatusStreamer> {
  private final Provider<SignalRepository> signalRepositoryProvider;

  public LiveStatusStreamer_MembersInjector(Provider<SignalRepository> signalRepositoryProvider) {
    this.signalRepositoryProvider = signalRepositoryProvider;
  }

  public static MembersInjector<LiveStatusStreamer> create(
      Provider<SignalRepository> signalRepositoryProvider) {
    return new LiveStatusStreamer_MembersInjector(signalRepositoryProvider);
  }

  @Override
  public void injectMembers(LiveStatusStreamer instance) {
    injectSignalRepository(instance, signalRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.georescue.victim.data.LiveStatusStreamer.signalRepository")
  public static void injectSignalRepository(LiveStatusStreamer instance,
      SignalRepository signalRepository) {
    instance.signalRepository = signalRepository;
  }
}
