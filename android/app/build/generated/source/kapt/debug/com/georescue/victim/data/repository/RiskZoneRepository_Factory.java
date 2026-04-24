package com.georescue.victim.data.repository;

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
public final class RiskZoneRepository_Factory implements Factory<RiskZoneRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public RiskZoneRepository_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public RiskZoneRepository get() {
    return newInstance(firestoreProvider.get());
  }

  public static RiskZoneRepository_Factory create(Provider<FirebaseFirestore> firestoreProvider) {
    return new RiskZoneRepository_Factory(firestoreProvider);
  }

  public static RiskZoneRepository newInstance(FirebaseFirestore firestore) {
    return new RiskZoneRepository(firestore);
  }
}
