package com.networking.model

import com.networking.JsonKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class CardTokenDto(

    @Json(name = JsonKey.CARD_ID)
    val card_number: String,
    @Json(name = JsonKey.CUSTOMER_ID)
    val customer_id: String,
    @Json(name = JsonKey.SECURITY_CODE)
    val security_code: String,
    val expiration_month: Int,
    val expiration_year: Int
)

