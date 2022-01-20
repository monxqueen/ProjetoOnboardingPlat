package com.example.nearby.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nearby.domain.entity.NearbyStores

internal class DiffCallBack : DiffUtil.ItemCallback<NearbyStores>() {
    override fun areItemsTheSame(oldItem: NearbyStores, newItem: NearbyStores) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NearbyStores, newItem: NearbyStores) =
        oldItem == newItem
}