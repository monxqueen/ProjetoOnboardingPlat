package com.example.storeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.favorite.presentation.FavoriteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnTest : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTest = findViewById(R.id.btnTest)
        btnTest.setOnClickListener {
            onClickBtnTest()
        }
    }

    private fun onClickBtnTest() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }
}