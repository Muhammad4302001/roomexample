package com.example.favorite.database.repository

import android.util.Log
import com.example.favorite.database.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

class FavoriteRepository(private val favoriteDao: FavoriteDao) {

    fun getAll()  = flow {
        Log.d("REPO", "getAll: ")
        emit(favoriteDao.findAll())
    }.flowOn(Dispatchers.IO)

    fun isExists(id: Int)  = flow {
        emit(favoriteDao.isExists(id))
    }.flowOn(Dispatchers.IO)

    fun insert(favorite: Favorite) {
        runBlocking(Dispatchers.IO) {
            favoriteDao.addFavorite(favorite)
        }
    }

    fun remove(id: Int) {
        runBlocking(Dispatchers.IO) {
            favoriteDao.remove(id)
        }
    }
}