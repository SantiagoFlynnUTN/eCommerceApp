package com.networking.base

import com.networking.JsonKey
import com.squareup.moshi.Json

data class ErrorResponse(
    @Json(name = JsonKey.MESSAGE)
    val message: String,
    @Json(name = JsonKey.TYPE)
    val type: String,
    @Json(name = JsonKey.CODE)
    val code: Int
)
