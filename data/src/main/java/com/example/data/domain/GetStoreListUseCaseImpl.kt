package com.example.data.domain

internal class GetStoreListUseCaseImpl(private val repository: Repository) : GetStoreListUseCase {
    override fun getList() = repository.getStores()
}