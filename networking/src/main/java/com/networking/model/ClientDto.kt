package com.networking.model

import com.networking.JsonKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ClientDto(
    @Json(name = JsonKey.EMAIL)
    val email: String
)
