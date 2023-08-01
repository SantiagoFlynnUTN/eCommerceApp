package com.networking

interface BaseDto<Any> {
    fun toDomainModel(): Any
}
