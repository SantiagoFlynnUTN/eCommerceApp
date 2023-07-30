package com.networking.model

import com.domain.model.FeedItem
import com.networking.JsonKey
import com.squareup.moshi.Json

data class FeedItemDto(
    @Json(name = JsonKey.ID)
    val id: Int,
    @Json(name = JsonKey.NAME)
    val name: String,
) {
    fun toDomainModel(): FeedItem {
        return FeedItem(
            id = id,
            name = name,
        )
    }
}
