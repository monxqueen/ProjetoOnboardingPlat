package com.example.favorite.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.favorite.databinding.ItemFavoriteStoresBinding
import com.example.favorite.domain.entity.FavoriteStore
import com.example.favorite.presentation.utils.ImageLoader
import com.example.favorite.presentation.utils.ImageLoaderImpl

class FavoriteStoresAdapter(private val context: Context,
                            var dataSet: MutableList<FavoriteStore> = mutableListOf()) : RecyclerView.Adapter<FavoriteStoreViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteStoreViewHolder {
        val binding = ItemFavoriteStoresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FavoriteStoreViewHolder(binding, ImageLoaderImpl())
    }

    override fun onBindViewHolder(viewHolder: FavoriteStoreViewHolder, position: Int) =
        viewHolder.bind(dataSet, position, context)

    override fun getItemCount() = dataSet.size
}

class FavoriteStoreViewHolder(private val binding: ItemFavoriteStoresBinding,
                              private val imageLoader: ImageLoader) : RecyclerView.ViewHolder(binding.root) {
    fun bind(dataSet: MutableList<FavoriteStore>, position: Int, context: Context) {
        binding.storeName.text = dataSet[position].name

        dataSet[position].iconUrl?.let {
            imageLoader.loadImage(context, it, binding.storeIconUrl)
        }
    }
}