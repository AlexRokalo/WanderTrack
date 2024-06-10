package com.ar.wandertrack.domain.repository

import android.content.Context
import com.ar.wandertrack.domain.model.User

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Result<User>

    @Deprecated("Use signInWithCredentialManager instead")
    suspend fun signInWithGoogle(idToken: String): Result<User>
    suspend fun signInWithCredentialManager(context: Context): Result<User>
    suspend fun signOut(): Result<Unit>
    suspend fun getCurrentUser(): Result<User?>
}