package com.example.data

import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.model.StoreResponse
import com.example.data.domain.Repository
import com.example.data.domain.entity.Store
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DataTest {

    //TODO: Verificar como testar o repositoryImpl sem precisar fazer chamada no Retrofit
    private val repository: Repository = mock(Repository::class.java)
    private val mapper = StoresMapper()

    @Test
    fun `when call getStoresList should return an expected list`() {

        //given
        val listStore = getStoresList()

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
        val listStore = getEmptyList()

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
        //TODO: Implementar essa função de teste
    }

    private fun getStoresList() =
        listOf(
            Store(
                2,
                "Lojas Americanas",
                "icone.jpg",
                -141545.05264,
                -2545875.56450
            )
        )

    private fun getEmptyList() = emptyList<Store>()
}