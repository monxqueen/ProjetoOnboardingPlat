package com.example.favorite.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favorite.R
import com.example.favorite.databinding.ItemFavoriteStoresBinding
import com.example.favorite.domain.entity.FavoriteStore

class FavoriteStoresAdapter(private val context: Context,
                            var dataSet: MutableList<FavoriteStore> = mutableListOf()) : RecyclerView.Adapter<FavoriteStoresAdapter.FavoriteStoreViewHolder>() {

    inner class FavoriteStoreViewHolder(val binding: ItemFavoriteStoresBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteStoreViewHolder {
        val binding = ItemFavoriteStoresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return FavoriteStoreViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: FavoriteStoreViewHolder, position: Int) {
        viewHolder.binding.storeName.text = dataSet[position].name
        Glide.with(context)
            .load(dataSet[position].iconUrl)
//            .placeholder(R.drawable.cast_1)
            .into(viewHolder.binding.storeIconUrl)
    }

    override fun getItemCount() = dataSet.size
}