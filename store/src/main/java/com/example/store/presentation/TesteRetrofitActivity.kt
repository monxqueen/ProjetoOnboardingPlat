package com.example.store.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.data.remote.repository.RepositoryImpl
import com.example.store.domain.GetStoreListUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TesteRetrofitActivity : AppCompatActivity() {

    private lateinit var rvAdapter: StoresAdapter
    private val useCase = GetStoreListUseCase(RepositoryImpl(StoresMapper()))
    private val disposable = CompositeDisposable()

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
            useCase.getList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    rvAdapter.dataSet.addAll(it)
                },
                {
                }
            ).handleDisposable()
    }

    private fun Disposable.handleDisposable(): Disposable = apply { disposable.add(this) }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}