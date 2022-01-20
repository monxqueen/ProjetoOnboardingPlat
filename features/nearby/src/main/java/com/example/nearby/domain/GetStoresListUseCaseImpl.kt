package com.example.nearby.domain

import com.example.data.domain.GetStoresListDataSourceUseCase
import com.example.nearby.domain.mapper.StoresMapper

internal class GetStoresListUseCaseImpl(private val getStoresListDataSourceUseCase: GetStoresListDataSourceUseCase,
                                        private val storesMapper: StoresMapper
) : GetStoresListUseCase {
    override fun invoke() = getStoresListDataSourceUseCase().map { storeList ->
        storesMapper.mapStoresListToDomain(storeList)
    }
}