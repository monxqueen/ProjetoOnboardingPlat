package com.example.store.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.data.remote.repository.RepositoryImpl
import com.example.store.domain.GetStoreListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TesteRetrofitActivity : AppCompatActivity() {

    private lateinit var rvAdapter: StoresAdapter
    private val useCase = GetStoreListUseCase(RepositoryImpl(StoresMapper()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste_retrofit)

        setupRecyclerView()
        getStoreList()
    }

    private fun setupRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.rvStores)
        rvAdapter = StoresAdapter()
        rv.adapter = rvAdapter
    }

    private fun getStoreList() {
            val service = useCase.getList()
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