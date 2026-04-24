package com.georescue.victim.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class LiveStatusStreamer_Factory implements Factory<LiveStatusStreamer> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseDatabase> databaseProvider;

  public LiveStatusStreamer_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider) {
    this.authProvider = authProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LiveStatusStreamer get() {
    return newInstance(authProvider.get(), databaseProvider.get());
  }

  public static LiveStatusStreamer_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider) {
    return new LiveStatusStreamer_Factory(authProvider, databaseProvider);
  }

  public static LiveStatusStreamer newInstance(FirebaseAuth auth, FirebaseDatabase database) {
    return new LiveStatusStreamer(auth, database);
  }
}
