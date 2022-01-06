package com.example.nearby.domain

import android.location.Location.distanceBetween
import com.example.nearby.domain.entity.NearbyStores
import com.example.nearby.domain.entity.Stores
import com.example.nearby.domain.entity.UserLocation
import io.reactivex.Single

internal class GetNearbyStoresUseCaseImpl(private val getStoresListUseCase: GetStoresListUseCase,
                                          private val getUserLocationUseCase: GetUserLocationUseCase) : GetNearbyStoresUseCase {

    override fun invoke() = getNearbyStoreList()

    private fun getNearbyStoreList() =
        Single.zip(
            getStoresListUseCase(),
            getUserLocationUseCase(),
            { storesList, userLocation ->
                evaluateDistance(storesList, userLocation)
            }
        )

    private fun evaluateDistance(storesList: List<Stores>, userLocation: UserLocation) : List<NearbyStores> {

        val distanceArray = FloatArray(1)
        val distance by lazy { distanceArray[0] }

        storesList.forEach { store ->
            distanceBetween(userLocation.latitude, userLocation.longitude,
                store.latitude, store.longitude, distanceArray)
        }
        return storesList.map { it.mapToNearbyStoreWithDistance(distance.convertToKilometers()) }
    }

    private fun Stores.mapToNearbyStoreWithDistance(distance: Float) =
        NearbyStores(
            id = id,
            name = name,
            iconUrl = iconUrl,
            distance = distance
        )

    private fun Float.convertToKilometers() = this/1000
}