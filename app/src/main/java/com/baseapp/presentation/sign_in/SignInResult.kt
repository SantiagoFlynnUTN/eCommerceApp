package com.baseapp.presentation.sign_in

data class SignInResult(
    val data: UserData?,
    val errorMsg: String?,
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?,
)
