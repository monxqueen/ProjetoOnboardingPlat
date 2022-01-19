package com.example.nearby.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
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