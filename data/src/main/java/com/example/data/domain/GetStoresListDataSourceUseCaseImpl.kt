package com.example.data.domain

internal class GetStoresListDataSourceUseCaseImpl(private val repository: Repository) : GetStoresListDataSourceUseCase {
    override operator fun invoke() = repository()
}