package com.example.favorite.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.favorite.R
import com.example.favorite.domain.entity.FavoriteStore

class FavoriteStoresAdapter(private val context: Context,
                            var dataSet: MutableList<FavoriteStore> = mutableListOf()) : RecyclerView.Adapter<FavoriteStoresAdapter.FavoriteStoreViewHolder>() {

    inner class FavoriteStoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemFavoriteStoreId: TextView = view.findViewById(R.id.storeId)
        val itemFavoriteStoreName: TextView = view.findViewById(R.id.storeName)
        val itemFavoriteStoreIconUrl: ImageView = view.findViewById(R.id.storeIconUrl)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FavoriteStoreViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_favorite_stores, viewGroup, false)

        return FavoriteStoreViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FavoriteStoreViewHolder, position: Int) {
        viewHolder.itemFavoriteStoreId.text = dataSet[position].id.toString()
        viewHolder.itemFavoriteStoreName.text = dataSet[position].name

        Glide.with(context)
            .load(dataSet[position].iconUrl)
//            .placeholder(R.drawable.cast_1)
            .into(viewHolder.itemFavoriteStoreIconUrl)
    }

    override fun getItemCount() = dataSet.size
}