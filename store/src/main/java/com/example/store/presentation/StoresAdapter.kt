package com.example.store.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.domain.entity.Store
import org.w3c.dom.Text

class StoresAdapter(var dataSet: MutableList<Store> = mutableListOf()) : RecyclerView.Adapter<StoresAdapter.CastViewHolder>() {

    inner class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.idStore)
        val name: TextView = view.findViewById(R.id.nameStore)
        val icon: TextView = view.findViewById(R.id.iconUrl)
        val latitude: TextView = view.findViewById(R.id.latitude)
        val longitude: TextView = view.findViewById(R.id.longitude)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_stores, viewGroup, false)

        return CastViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: CastViewHolder, position: Int) {
        viewHolder.id.text = dataSet[position].id.toString()
        viewHolder.name.text = dataSet[position].name
        viewHolder.icon.text = dataSet[position].iconUrl
        viewHolder.latitude.text = dataSet[position].latitude.toString()
        viewHolder.longitude.text = dataSet[position].longitude.toString()
    }

    override fun getItemCount() = dataSet.size
}