package com.example.storeapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.favorite.presentation.FavoriteFragment

private const val NUMBER_OF_FRAGMENTS = 2

class ViewPagerFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = NUMBER_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            FragmentPosition.FIRST_FRAGMENT_POSITION.position -> FavoriteFragment()
            else -> FavoriteFragment() //TODO: encontrar maneira de retornar uma exception (a função necessariamente retorna uma fragment).
        }
    }
}