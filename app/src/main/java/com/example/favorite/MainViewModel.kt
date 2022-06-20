package com.example.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.favorite.database.FavoriteDatabase
import com.example.favorite.database.model.Favorite
import com.example.favorite.database.repository.FavoriteRepository
import com.example.favorite.presentation.Metan
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = FavoriteDatabase.getDatabase(application)
    private val favoriteRepository = FavoriteRepository(database.favoriteDao())

    fun allMetans() = liveData {
        val favFlow = favoriteRepository.getAll()
        val metFlow = getMetans()

        favFlow.combine(metFlow) { f, m -> Pair(f, m) }.collectLatest {
            emit(it)
        }

    }

    private fun getMetans() = flow {
        emit(
            listOf(
                Metan(12, "Honka"),
                Metan(122, "Forish"),
                Metan(112, "Urganch GorGaz"),
                Metan(123, "Xiva YoqGAz"),
                Metan(148, "Malibu")
            )
        )
    }

    fun removeFavorite(metan: Metan) {
        favoriteRepository.remove(metan.id)
    }

    fun insertFavorite(metan: Metan) {
        favoriteRepository.insert(
            Favorite(0, metan.id)
        )
    }
}