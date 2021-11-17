package com.example.favorite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.R
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.adapter.FavoriteStoresAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var rvAdapter: FavoriteStoresAdapter
    private val favoriteViewModel = FavoriteViewModel(
        GetFavoriteListUseCaseImpl(
            GetStoreListUseCaseImpl(
                RepositoryImpl(
                    StoresMapper()
                )), FavoriteStoresMapper()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteViewModel.getFavoriteList()
        setupFavoriteStoresRecyclerView()
        observeFavoriteList()
        observeErrorState()
    }

    private fun setupFavoriteStoresRecyclerView() {
        val rvFavoriteStores = findViewById<RecyclerView>(R.id.rvFavoriteStoresList)
        rvAdapter = FavoriteStoresAdapter(this)
        rvFavoriteStores.adapter = rvAdapter
        rvFavoriteStores.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeFavoriteList() {
        favoriteViewModel.storeLiveData.observe(this,
            { response ->
                response?.let {
                    rvAdapter.dataSet = it.toMutableList()
                    rvAdapter.notifyDataSetChanged()
                }
            }
        )
    }

    private fun observeErrorState() {
        favoriteViewModel.errorStateLiveData.observe(this, { error ->
            Toast.makeText(this, "Ocorreu um erro :(", Toast.LENGTH_SHORT).show()
        })

    }

}