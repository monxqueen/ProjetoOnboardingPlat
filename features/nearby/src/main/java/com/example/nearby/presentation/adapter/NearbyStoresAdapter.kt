package com.example.nearby.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadImage
import com.example.nearby.databinding.ItemNearbyStoresBinding
import com.example.nearby.domain.entity.NearbyStores

internal class NearbyStoresAdapter : ListAdapter<NearbyStores, NearbyStoresViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NearbyStoresViewHolder {
        val binding = ItemNearbyStoresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return NearbyStoresViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NearbyStoresViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }
}

internal class DiffCallBack : DiffUtil.ItemCallback<NearbyStores>() {
    override fun areItemsTheSame(oldItem: NearbyStores, newItem: NearbyStores) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NearbyStores, newItem: NearbyStores) =
        oldItem == newItem
}

internal class NearbyStoresViewHolder(private val binding: ItemNearbyStoresBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NearbyStores) {
        binding.storeName.text = item.name
        binding.distanceBetweenDeviceAndStore.text = item.distance.toString()

        val storeIcon = binding.storeIconUrl
        item.iconUrl?.let { storeIcon.loadImage(it) }
    }
}