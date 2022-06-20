package com.example.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite.presentation.ItemAdapter
import com.example.favorite.presentation.Metan
import com.example.favorite.presentation.MetanCallback

class MainActivity : AppCompatActivity(), MetanCallback {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        viewModel.allMetans().observe(this) { pair ->
            val (favorites, metans) = pair
            Log.d("Observe", "onCreate: allMetans, $pair")
            recycler.adapter = ItemAdapter(metans, favorites, this)
        }
    }

    override fun add(metan: Metan) {
        viewModel.insertFavorite(metan)
    }

    override fun remove(metan: Metan) {
        viewModel.removeFavorite(metan)
    }
}