package com.example.storeapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite.presentation.FavoriteFragment

private const val NUMBER_OF_FRAGMENTS = 2
const val FIRST_FRAGMENT_POSITION = 0

class ViewPagerFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FIRST_FRAGMENT_POSITION -> FavoriteFragment()
            else -> FavoriteFragment()
        }
    }
}