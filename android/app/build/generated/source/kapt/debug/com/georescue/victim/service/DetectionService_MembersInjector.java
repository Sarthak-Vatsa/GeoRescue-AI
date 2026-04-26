package com.georescue.victim.service;

import com.georescue.victim.data.LiveStatusStreamer;
import com.georescue.victim.domain.usecases.FailsafeTimer;
import com.georescue.victim.domain.usecases.InactivityUseCase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
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
public final class DetectionService_MembersInjector implements MembersInjector<DetectionService> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseDatabase> databaseProvider;

  private final Provider<LiveStatusStreamer> liveStatusStreamerProvider;

  private final Provider<InactivityUseCase> inactivityUseCaseProvider;

  private final Provider<FailsafeTimer> failsafeTimerProvider;

  public DetectionService_MembersInjector(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<LiveStatusStreamer> liveStatusStreamerProvider,
      Provider<InactivityUseCase> inactivityUseCaseProvider,
      Provider<FailsafeTimer> failsafeTimerProvider) {
    this.authProvider = authProvider;
    this.databaseProvider = databaseProvider;
    this.liveStatusStreamerProvider = liveStatusStreamerProvider;
    this.inactivityUseCaseProvider = inactivityUseCaseProvider;
    this.failsafeTimerProvider = failsafeTimerProvider;
  }

  public static MembersInjector<DetectionService> create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<LiveStatusStreamer> liveStatusStreamerProvider,
      Provider<InactivityUseCase> inactivityUseCaseProvider,
      Provider<FailsafeTimer> failsafeTimerProvider) {
    return new DetectionService_MembersInjector(authProvider, databaseProvider, liveStatusStreamerProvider, inactivityUseCaseProvider, failsafeTimerProvider);
  }

  @Override
  public void injectMembers(DetectionService instance) {
    injectAuth(instance, authProvider.get());
    injectDatabase(instance, databaseProvider.get());
    injectLiveStatusStreamer(instance, liveStatusStreamerProvider.get());
    injectInactivityUseCase(instance, inactivityUseCaseProvider.get());
    injectFailsafeTimer(instance, failsafeTimerProvider.get());
  }

  @InjectedFieldSignature("com.georescue.victim.service.DetectionService.auth")
  public static void injectAuth(DetectionService instance, FirebaseAuth auth) {
    instance.auth = auth;
  }

  @InjectedFieldSignature("com.georescue.victim.service.DetectionService.database")
  public static void injectDatabase(DetectionService instance, FirebaseDatabase database) {
    instance.database = database;
  }

  @InjectedFieldSignature("com.georescue.victim.service.DetectionService.liveStatusStreamer")
  public static void injectLiveStatusStreamer(DetectionService instance,
      LiveStatusStreamer liveStatusStreamer) {
    instance.liveStatusStreamer = liveStatusStreamer;
  }

  @InjectedFieldSignature("com.georescue.victim.service.DetectionService.inactivityUseCase")
  public static void injectInactivityUseCase(DetectionService instance,
      InactivityUseCase inactivityUseCase) {
    instance.inactivityUseCase = inactivityUseCase;
  }

  @InjectedFieldSignature("com.georescue.victim.service.DetectionService.failsafeTimer")
  public static void injectFailsafeTimer(DetectionService instance, FailsafeTimer failsafeTimer) {
    instance.failsafeTimer = failsafeTimer;
  }
}
