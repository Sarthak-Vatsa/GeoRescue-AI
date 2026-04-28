package com.georescue.victim.data;

import com.google.android.gms.location.FusedLocationProviderClient;
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

  private final Provider<FusedLocationProviderClient> fusedLocationProvider;

  private final Provider<BatteryReader> batteryReaderProvider;

  public LiveStatusStreamer_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<FusedLocationProviderClient> fusedLocationProvider,
      Provider<BatteryReader> batteryReaderProvider) {
    this.authProvider = authProvider;
    this.databaseProvider = databaseProvider;
    this.fusedLocationProvider = fusedLocationProvider;
    this.batteryReaderProvider = batteryReaderProvider;
  }

  @Override
  public LiveStatusStreamer get() {
    return newInstance(authProvider.get(), databaseProvider.get(), fusedLocationProvider.get(), batteryReaderProvider.get());
  }

  public static LiveStatusStreamer_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseDatabase> databaseProvider,
      Provider<FusedLocationProviderClient> fusedLocationProvider,
      Provider<BatteryReader> batteryReaderProvider) {
    return new LiveStatusStreamer_Factory(authProvider, databaseProvider, fusedLocationProvider, batteryReaderProvider);
  }

  public static LiveStatusStreamer newInstance(FirebaseAuth auth, FirebaseDatabase database,
      FusedLocationProviderClient fusedLocation, BatteryReader batteryReader) {
    return new LiveStatusStreamer(auth, database, fusedLocation, batteryReader);
  }
}
