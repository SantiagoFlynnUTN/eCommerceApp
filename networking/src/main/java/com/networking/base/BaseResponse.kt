package com.networking.base

import com.networking.JsonKey
import com.squareup.moshi.Json

data class BaseResponse<T>(
    @Json(name = JsonKey.PATH)
    val path: String,
    @Json(name = JsonKey.VERSION)
    val version: String,
    @Json(name = JsonKey.STATUS)
    val status: String,
    @Json(name = JsonKey.TIMESTAMP)
    val timestamp: String,

    @Json(name = JsonKey.DATA)
    val data: T?,
    @Json(name = JsonKey.ERROR)
    val error: ErrorResponse?
)
