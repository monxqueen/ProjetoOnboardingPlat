package com.example.storeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storeapp.R
import com.example.storeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewPagerFragmentAdapter: ViewPagerFragmentAdapter by lazy {
        ViewPagerFragmentAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewPagerAdapter()
        setupBottomNav()
    }

    private fun setupBottomNav() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorite -> binding.vwpFragmentContainer.currentItem =
                    FragmentPosition.FAVORITE.ordinal
                R.id.nearby -> binding.vwpFragmentContainer.currentItem =
                    FragmentPosition.NEARBY.ordinal
            }
            true
        }
    }

    private fun setupViewPagerAdapter() {
        binding.vwpFragmentContainer.adapter = viewPagerFragmentAdapter
    }
}