package com.example.data.domain

import com.example.store.domain.Repository


class GetStoreListUseCaseImpl(private val repository: Repository) : GetStoreListUseCase {
    override fun getList() = repository.getStores()
}