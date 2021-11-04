package com.example.store

import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.data.remote.model.StoreResponse
import com.example.store.data.remote.repository.RepositoryImpl
import com.example.store.domain.entity.Store
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class StoreTest {

    @Mock
    private val repository: RepositoryImpl = mock(RepositoryImpl::class.java)
    private val mapper = StoresMapper()

    @Test
    fun `when call getStoresList should return an expected list`() {
        //given
        val listStore = STORES_LIST

        `when`(repository.getStores()).thenReturn(Single.just(listStore))

        val expected = listOf(
            Store(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )

        //when
        val result = repository.getStores()


        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call getStoresList should return an empty list`() {
        //given
        val listStore = EMPTY_LIST

        `when`(repository.getStores()).thenReturn(Single.just(listStore))

        val expected = emptyList<Store>()

        //when
        val result = repository.getStores()

        //then
        result.test().assertNoErrors().assertValue(expected)
    }

    @Test
    fun `when call mapStoresListToDomain should return a Store-typed list`() {
        //given
        val expected = listOf(
            Store(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )
        val storeResponseList = listOf(
            StoreResponse(
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
        val expected = emptyList<Store>()

        val storeResponseList = emptyList<StoreResponse>()

        //when
        val result = mapper.mapStoresListToDomain(storeResponseList)

        //then
        assertEquals(expected, result)
    }

    @Test
    fun `when call mapStoresListToDomain with different attribute type should return an error`(){
        
    }


    companion object {
        //val STORE = Store(1, "Lojas Americanas", "icone.jpg", -141545.05264, -2545875.56450)

        val EMPTY_LIST = emptyList<Store>()

        val STORES_LIST =
            listOf(
                Store(
                    2,
                    "Lojas Americanas",
                    "icone.jpg",
                    -141545.05264,
                    -2545875.56450
                )
            )

        val STORE_RESPONSE_LIST =
            listOf(
                StoreResponse(
                    2,
                    "Lojas Americanas",
                    "icone.jpg",
                    -141545.05264,
                    -2545875.56450
                )
            )
    }
}