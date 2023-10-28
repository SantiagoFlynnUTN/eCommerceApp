package com.domain.usecase

interface IAuthenticationUseCase {

    fun login(username: String, password: String)
}
