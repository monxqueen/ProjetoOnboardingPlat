package com.example.store.domain


class GetStoreListUseCaseImpl(private val repository: Repository) : GetStoreListUseCase {
    override fun getList() = repository.getStores()
}