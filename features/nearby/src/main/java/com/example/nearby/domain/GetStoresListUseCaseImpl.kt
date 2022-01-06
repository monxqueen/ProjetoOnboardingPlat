package com.example.nearby.domain

import com.example.data.domain.GetStoreListUseCase
import com.example.nearby.domain.mapper.StoresMapper

internal class GetStoresListUseCaseImpl(private val storeListUseCase: GetStoreListUseCase,
                                        private val storesMapper: StoresMapper
) : GetStoresListUseCase {
    override fun invoke() = storeListUseCase.getList().map { storeList ->
        storesMapper.mapStoresListToDomain(storeList)
    }
}