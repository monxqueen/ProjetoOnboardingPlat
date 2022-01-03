package com.example.nearby.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.nearby.data.model.UserLocation
import com.example.nearby.databinding.FragmentNearbyBinding
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val REQUEST_LOCATION_PERMISSIONS = 1

class NearbyFragment : Fragment() {

    private val binding: FragmentNearbyBinding by lazy {
        FragmentNearbyBinding.inflate(layoutInflater)
    }

    private val viewModel: NearbyViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        checkPermissions()
        observeState()
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
            return
        }

        getUserLocation()
    }

    //TODO: mover para o LocationDeviceDataSourceImpl (qual seria o trigger pra executar essa função lá?!)
    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        LocationServices.getFusedLocationProviderClient(requireContext()).lastLocation.addOnSuccessListener { apiLocation ->
            apiLocation?.let {
                val userLocation = UserLocation(
                    latitude = apiLocation.latitude,
                    longitude = apiLocation.longitude
                )

                //TODO: isso sairia daqui e ficaria dentro do observeState()
                binding.txtLatitude.text = userLocation.latitude.toString()
                binding.txtLongitude.text = userLocation.longitude.toString()
            }
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_LOCATION_PERMISSIONS
        )
    }


    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->

            with(state) {
                userLocation?.let {
                    binding.txtLatitude.text =  it.latitude.toString()
                    binding.txtLongitude.text = it.longitude.toString()
                }
            }
        })
    }
}