package com.georescue.victim.service;

import com.georescue.victim.data.LiveStatusStreamer;
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

  public DetectionService_MembersInjector(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<LiveStatusStreamer> liveStatusStreamerProvider) {
    this.authProvider = authProvider;
    this.databaseProvider = databaseProvider;
    this.liveStatusStreamerProvider = liveStatusStreamerProvider;
  }

  public static MembersInjector<DetectionService> create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<LiveStatusStreamer> liveStatusStreamerProvider) {
    return new DetectionService_MembersInjector(authProvider, databaseProvider, liveStatusStreamerProvider);
  }

  @Override
  public void injectMembers(DetectionService instance) {
    injectAuth(instance, authProvider.get());
    injectDatabase(instance, databaseProvider.get());
    injectLiveStatusStreamer(instance, liveStatusStreamerProvider.get());
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
}
