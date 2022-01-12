package com.example.data

import com.example.data.data.remote.StoreService
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.model.StoreDataSourceResponse
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.entity.StoreDataSource
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class RepositoryTest {

    private val mapper = StoresMapper()
    private val service: StoreService = mock()
    private val repository = RepositoryImpl(mapper, service)

    @Test
    fun `when call getStoresList should return an expected list`() {
        //given
        val listStore = getStoresResponseList()

        whenever(service.getStoresList()).thenReturn(Single.just(listStore))

        val expected = listOf(
            StoreDataSource(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )

        //when
        val result = repository()

        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call getStoresList should return an empty list`() {
        //given
        val listStore = Single.just(emptyList<StoreDataSourceResponse>())

        whenever(service.getStoresList()).thenReturn(listStore)

        val expected = emptyList<StoreDataSource>()

        //when
        val result = repository()

        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call mapStoresListToDomain should return a Store-typed list`() {
        //given
        val expected = listOf(
            StoreDataSource(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )
        val storeResponseList = listOf(
            StoreDataSourceResponse(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )

        //when
        val result = mapper.mapStoresListToDomain(storeResponseList)

        //then
        assertEquals(expected, result)
    }

    @Test
    fun `when call mapStoresListToDomain should return an empty list`() {
        //given
        val expected = emptyList<StoreDataSource>()

        val storeResponseList = emptyList<StoreDataSourceResponse>()

        //when
        val result = mapper.mapStoresListToDomain(storeResponseList)

        //then
        assertEquals(expected, result)
    }

    private fun getStoresResponseList() =
        listOf(
            StoreDataSourceResponse(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )


}