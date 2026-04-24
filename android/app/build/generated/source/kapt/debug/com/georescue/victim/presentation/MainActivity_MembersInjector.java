package com.georescue.victim.presentation;

import com.georescue.victim.data.repository.AuthRepository;
import com.georescue.victim.data.repository.RiskZoneRepository;
import com.georescue.victim.service.GeofenceManager;
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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<RiskZoneRepository> riskZoneRepositoryProvider;

  private final Provider<GeofenceManager> geofenceManagerProvider;

  public MainActivity_MembersInjector(Provider<AuthRepository> authRepositoryProvider,
      Provider<RiskZoneRepository> riskZoneRepositoryProvider,
      Provider<GeofenceManager> geofenceManagerProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.riskZoneRepositoryProvider = riskZoneRepositoryProvider;
    this.geofenceManagerProvider = geofenceManagerProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<AuthRepository> authRepositoryProvider,
      Provider<RiskZoneRepository> riskZoneRepositoryProvider,
      Provider<GeofenceManager> geofenceManagerProvider) {
    return new MainActivity_MembersInjector(authRepositoryProvider, riskZoneRepositoryProvider, geofenceManagerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAuthRepository(instance, authRepositoryProvider.get());
    injectRiskZoneRepository(instance, riskZoneRepositoryProvider.get());
    injectGeofenceManager(instance, geofenceManagerProvider.get());
  }

  @InjectedFieldSignature("com.georescue.victim.presentation.MainActivity.authRepository")
  public static void injectAuthRepository(MainActivity instance, AuthRepository authRepository) {
    instance.authRepository = authRepository;
  }

  @InjectedFieldSignature("com.georescue.victim.presentation.MainActivity.riskZoneRepository")
  public static void injectRiskZoneRepository(MainActivity instance,
      RiskZoneRepository riskZoneRepository) {
    instance.riskZoneRepository = riskZoneRepository;
  }

  @InjectedFieldSignature("com.georescue.victim.presentation.MainActivity.geofenceManager")
  public static void injectGeofenceManager(MainActivity instance, GeofenceManager geofenceManager) {
    instance.geofenceManager = geofenceManager;
  }
}
