package com.example.store.domain

class GetStoreListUseCase(private val repository: Repository) {
    fun getList() = repository.getStores()
}