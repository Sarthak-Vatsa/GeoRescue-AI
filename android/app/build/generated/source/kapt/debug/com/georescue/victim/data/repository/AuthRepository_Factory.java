package com.georescue.victim.data.repository;

import android.content.Context;
import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<Context> contextProvider;

  public AuthRepository_Factory(Provider<FirebaseAuth> authProvider,
      Provider<Context> contextProvider) {
    this.authProvider = authProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(authProvider.get(), contextProvider.get());
  }

  public static AuthRepository_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<Context> contextProvider) {
    return new AuthRepository_Factory(authProvider, contextProvider);
  }

  public static AuthRepository newInstance(FirebaseAuth auth, Context context) {
    return new AuthRepository(auth, context);
  }
}
