package com.example.storeapp.presentation

import android.view.InflateException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite.presentation.FavoriteFragment
import com.example.nearby.presentation.NearbyFragment

class ViewPagerFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = FragmentPosition.values().size

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FragmentPosition.FAVORITE.ordinal -> FavoriteFragment()
            FragmentPosition.NEARBY.ordinal -> NearbyFragment()
            else -> throw InflateException("Posição não implementada")
        }
    }
}