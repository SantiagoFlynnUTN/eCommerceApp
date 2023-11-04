package com.baseapp.presentation.sign_in

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val client: GoogleSignInClient
) {
    private val auth = Firebase.auth

    fun signIn(): Intent? {
        val result = try {
            client.signInIntent
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {

        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        val account = task.result
        val googleCredentials = GoogleAuthProvider.getCredential(account.idToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    getSignedUser()
                },
                errorMsg = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMsg = e.message)
        }
    }

    suspend fun signOut() {
        try {
            client.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    private fun getSignedUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            userName = displayName,
            profilePictureUrl = photoUrl.toString()
        )
    }
}