package com.example.store.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.domain.GetStoreListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TesteRetrofitActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: StoresAdapter
    private val useCase = GetStoreListUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rvStores)
        rvAdapter = StoresAdapter()
        rv.adapter = rvAdapter
        getStoreList()
    }

    private fun getStoreList() {
            useCase.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    rvAdapter.dataSet.addAll(it)
                },
                {
                }
            ).dispose()
    }
}