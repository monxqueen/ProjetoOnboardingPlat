package com.example.nearby.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadImage
import com.example.nearby.databinding.ItemNearbyStoresBinding
import com.example.nearby.domain.entity.NearbyStores

internal class NearbyStoresViewHolder(private val binding: ItemNearbyStoresBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NearbyStores) {
        binding.storeName.text = item.name
        binding.distanceBetweenDeviceAndStore.text = item.distance.toString()

        val storeIcon = binding.storeIconUrl
        item.iconUrl?.let { storeIcon.loadImage(it) }
    }
}