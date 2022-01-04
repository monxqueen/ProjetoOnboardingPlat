package com.example.nearby.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.nearby.databinding.FragmentNearbyBinding
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
        viewModel.getUserLocation()
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