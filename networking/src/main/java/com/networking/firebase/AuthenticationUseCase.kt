package com.networking.firebase

import com.domain.usecase.IAuthenticationUseCase
import com.google.firebase.auth.FirebaseAuth

class AuthenticationUseCase : IAuthenticationUseCase {
    override fun login(username: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
    }
}
