package com.example.favorite.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val item_id: Int
)