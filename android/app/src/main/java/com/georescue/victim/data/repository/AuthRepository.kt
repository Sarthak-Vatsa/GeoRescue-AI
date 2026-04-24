package com.georescue.victim.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    val currentUser: FirebaseUser?
        get() = auth.currentUser

    suspend fun signInAnonymously(): Result<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            val user = result.user ?: throw Exception("User is null")
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
