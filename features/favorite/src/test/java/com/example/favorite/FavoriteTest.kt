package com.example.favorite

import com.example.store.data.remote.mapper.StoresMapper
import com.example.store.data.remote.model.StoreResponse
import com.example.store.domain.Repository
import com.example.store.domain.entity.Store
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FavoriteTest {


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