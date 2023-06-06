package com.example.star_wars_app.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UpdateEntity(
    @PrimaryKey
    val id: Int,
    val favorite: Boolean,
    val lastSeen: String?
)
