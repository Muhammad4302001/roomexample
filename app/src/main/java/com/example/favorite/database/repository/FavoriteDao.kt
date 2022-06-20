package com.example.favorite.database.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.favorite.database.model.Favorite

@Dao
interface FavoriteDao {

    @Query("select * from Favorite;")
    fun findAll(): List<Favorite>

    @Insert
    fun addFavorite(favorite: Favorite)

    @Query("select exists(select * from Favorite where item_id = :item_id)")
    fun isExists(item_id: Int): Boolean

    @Query("delete from Favorite where item_id = :item_id")
    fun remove(item_id: Int)
}