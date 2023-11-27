package com.storage.burgers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BurgerEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)