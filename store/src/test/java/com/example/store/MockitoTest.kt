package com.example.store

import com.example.store.data.remote.repository.RepositoryImpl
import com.example.store.domain.entity.Store
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MockitoTest {

    @Mock
    private val repository: RepositoryImpl = mock(RepositoryImpl::class.java)

    @Test
    fun `when call getStoresList should return an expected list`() {
        val listStore = SINGLE_ACCOUNTS

        //given
        `when`(repository.getStores()).thenReturn(Single.just(listStore))

        //when
        val result = repository.getStores()

        val expected = listOf(
            Store(1, "loja", "icon", 1.0, 2.0)
        )

        //then
        result.test().assertNoErrors().assertValue(expected)

    }

    companion object {
        val EMPTY_ACCOUNTS = emptyList<Store>()

        val SINGLE_ACCOUNTS =
            listOf(
                Store(1, "loja", "icon", 1.0, 2.0)
            )
    }
}