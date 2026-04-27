package com.georescue.victim.data.repository;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
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
public final class SignalRepositoryImpl_Factory implements Factory<SignalRepositoryImpl> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FusedLocationProviderClient> fusedLocationProvider;

  public SignalRepositoryImpl_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider,
      Provider<FusedLocationProviderClient> fusedLocationProvider) {
    this.authProvider = authProvider;
    this.firestoreProvider = firestoreProvider;
    this.fusedLocationProvider = fusedLocationProvider;
  }

  @Override
  public SignalRepositoryImpl get() {
    return newInstance(authProvider.get(), firestoreProvider.get(), fusedLocationProvider.get());
  }

  public static SignalRepositoryImpl_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider,
      Provider<FusedLocationProviderClient> fusedLocationProvider) {
    return new SignalRepositoryImpl_Factory(authProvider, firestoreProvider, fusedLocationProvider);
  }

  public static SignalRepositoryImpl newInstance(FirebaseAuth auth, FirebaseFirestore firestore,
      FusedLocationProviderClient fusedLocation) {
    return new SignalRepositoryImpl(auth, firestore, fusedLocation);
  }
}
