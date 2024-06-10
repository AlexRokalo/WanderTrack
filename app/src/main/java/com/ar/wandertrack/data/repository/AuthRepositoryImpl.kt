package com.ar.wandertrack.data.repository

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import com.ar.wandertrack.data.model.UserEntity
import com.ar.wandertrack.data.model.mapper.Mapper
import com.ar.wandertrack.data.model.toUser
import com.ar.wandertrack.domain.model.User
import com.ar.wandertrack.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val mapper: Mapper<UserEntity, User>
) : AuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Result<User> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val user = result.user?.toUser()
            if (user != null) {
                Result.success(mapper.fromEntity(user))
            } else {
                Result.failure(Exception("User is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @Deprecated(
        "Use signInWithCredentialManager instead",
        ReplaceWith("signInWithCredentialManager(context:Context):Result<User>")
    )
    override suspend fun signInWithGoogle(idToken: String): Result<User> {
        return Result.failure(Exception("Deprecated Use signInWithCredentialManager instead"))
    }

    override suspend fun signInWithCredentialManager(context: Context): Result<User> {
        return try {
            val credentialManager = CredentialManager.create(context)
            val result = GetCredentialRequest.Builder()
                .addCredentialOption(GetPasswordOption())
                .build()

            Log.d("AuthRepositoryImpl", "result: ${result.origin}")
            val credentialResponse = credentialManager.getCredential(context, result)
            val credential = credentialResponse.credential

            Log.d("AuthRepositoryImpl", "signInWithCredentialManager: $credential")

            if (credential is PasswordCredential) {
                val email = credential.id
                val password = credential.password

                return signInWithEmailAndPassword(email, password)
            }

            return Result.failure(Exception("No valid credentials found"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signOut(): Result<Unit> {
        return try {
            Result.success(firebaseAuth.signOut())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCurrentUser(): Result<User?> {
        return try {
            val user = firebaseAuth.currentUser?.toUser()
            if (user != null) {
                Result.success(mapper.fromEntity(user))
            } else {
                Result.failure(Exception("User is null"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}