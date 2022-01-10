package com.example.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadImage
import com.example.favorite.databinding.ItemFavoriteStoresBinding
import com.example.favorite.domain.entity.FavoriteStore

internal class FavoriteStoresAdapter(var dataSet: MutableList<FavoriteStore> = mutableListOf()) : RecyclerView.Adapter<FavoriteStoreViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteStoreViewHolder {
        val binding = ItemFavoriteStoresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FavoriteStoreViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: FavoriteStoreViewHolder, position: Int) =
        viewHolder.bind(dataSet, position)

    override fun getItemCount() = dataSet.size
}

internal class FavoriteStoreViewHolder(private val binding: ItemFavoriteStoresBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dataSet: MutableList<FavoriteStore>, position: Int) {
        binding.storeName.text = dataSet[position].name

        val storeIcon = binding.storeIconUrl
        dataSet[position].iconUrl?.let { storeIcon.loadImage(it) }
    }
}