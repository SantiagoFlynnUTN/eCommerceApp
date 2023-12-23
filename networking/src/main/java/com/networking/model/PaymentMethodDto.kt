package com.networking.model

import com.squareup.moshi.Json


data class PaymentMethodDto(
    /** Id of the issuer. */
    val id: String,

    /** Name of the issuer. */
    val name: String
)
