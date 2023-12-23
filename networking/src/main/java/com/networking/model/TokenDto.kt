package com.networking.model

import com.networking.JsonKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TokenDto(
    @Json(name = JsonKey.TOKEN)
    val token: String,
    @Json(name = JsonKey.ISSUER)
    val issuer: CustomerCardIssuer,
    @Json(name = JsonKey.PAYMENT_METHOD_ID)
    val payment_method_id: String
)
