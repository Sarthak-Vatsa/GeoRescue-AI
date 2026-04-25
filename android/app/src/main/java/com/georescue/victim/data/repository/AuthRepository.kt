package com.georescue.victim.data.repository

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    @ApplicationContext private val context: Context // 1. Inject Application Context
) {
    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signInAnonymously(): Result<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            val user = result.user ?: throw Exception("User is null")

            // 2. Cache the UID the moment login is successful
            val sharedPrefs = context.getSharedPreferences("GeoRescuePrefs", Context.MODE_PRIVATE)
            sharedPrefs.edit { putString("USER_UID", user.uid) }

            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()

        // 3. (Optional but recommended) Clear the cached UID when signing out
        val sharedPrefs = context.getSharedPreferences("GeoRescuePrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit { remove("USER_UID") }
    }
}