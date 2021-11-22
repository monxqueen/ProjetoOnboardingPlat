package com.example.storeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storeapp.R
import com.example.storeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerFragmentAdapter: ViewPagerFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewPagerFragmentAdapter = ViewPagerFragmentAdapter(this)

        binding.apply {
            vwpFragmentContainer.adapter = viewPagerFragmentAdapter

            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.favorite -> vwpFragmentContainer.currentItem = FIRST_FRAGMENT_POSITION
                    R.id.nearby -> true //TODO: Vincular à fragment nearby
                }
                false
            }
        }
    }
}