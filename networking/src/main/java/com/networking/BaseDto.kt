package com.networking

interface BaseDto<Any> {
    fun toDomainModel(): Any

    /* Also could contain
    fun fromDomainModel(obj: Any)
    */
}
