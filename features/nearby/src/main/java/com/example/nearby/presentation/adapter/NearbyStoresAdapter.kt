package com.example.nearby.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.utils.loadImage
import com.example.nearby.databinding.ItemNearbyStoresBinding
import com.example.nearby.domain.entity.NearbyStores

internal class NearbyStoresAdapter(var dataSet: MutableList<NearbyStores> = mutableListOf()) : RecyclerView.Adapter<NearbyStoresViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NearbyStoresViewHolder {
        val binding = ItemNearbyStoresBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return NearbyStoresViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NearbyStoresViewHolder, position: Int) =
        viewHolder.bind(dataSet, position)

    override fun getItemCount() = dataSet.size
}

internal class NearbyStoresViewHolder(private val binding: ItemNearbyStoresBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(dataSet: MutableList<NearbyStores>, position: Int) {
        binding.storeName.text = dataSet[position].name
        binding.distanceBetweenDeviceAndStore.text = dataSet[position].distance.toString()

        val storeIcon = binding.storeIconUrl
        dataSet[position].iconUrl?.let { storeIcon.loadImage(it) }
    }
}