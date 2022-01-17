package com.example.nearby.presentation

import android.Manifest
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.databinding.FragmentStandardBinding
import com.example.nearby.presentation.adapter.NearbyStoresAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val REQUEST_LOCATION_PERMISSIONS = 1

class NearbyFragment : Fragment() {
    private val binding: FragmentStandardBinding by lazy {
        FragmentStandardBinding.inflate(layoutInflater)
    }

    private val rvAdapter: NearbyStoresAdapter by lazy {
        NearbyStoresAdapter()
    }

    private val viewModel: NearbyViewModel by viewModel()
    //objeto de pedir permissao
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
           viewModel.updatePermissionStatus(isGranted)
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()

       // viewModel.updatePermissionStatus(isPermissionGranted())

        checkPermissions()

        observePermission()
        observeState()
    }

    private fun setupRecyclerView() {
        binding.rvStoresList.let {
            it.adapter = rvAdapter
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupListeners() {
        binding.layoutError.btnError.setOnClickListener {
            onClickBtnTryAgain()
        }
    }

    private fun onClickBtnTryAgain() {
        viewModel.tryAgain()
    }

    private fun observePermission() {
        viewModel.isLocationPermissionGrantedLiveData.observe(viewLifecycleOwner, { permission ->
            if (!permission) {
                requestPermissions()
//                viewModel.onPermissionResult(isPermissionGranted())
            }
            // criava loop
            //viewModel.updatePermissionStatus(isPermissionGranted())
        })
    }

    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->

            with(state) {
                binding.apply {
                    rvStoresList.isVisible = nearbyList?.isNotEmpty() ?: false
                    txtEmptyResult.isVisible = nearbyList?.isEmpty() ?: false
                    layoutError.root.isVisible = isErrorVisible
                    progressBar.isVisible = isLoadingVisible
                }

//                if (!isLocationPermissionGranted) {
//                    requestPermissions()
//                    viewModel.updatePermissionStatus(isPermissionGranted())
////                    viewModel.onPermissionResult(isPermissionGranted())
//                }
//
////                else {
////                    viewModel.onPermissionResult(isPermissionGranted())
////                }

                nearbyList?.let {
                    rvAdapter.updateList(it)
                }
            }
        })
    }

    fun checkPermissions(){
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                        ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                viewModel.getNearbyStores()

            }
            shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION) -> {
                Toast.makeText( requireContext(),"Voce deve aceitar a permissao", Toast.LENGTH_SHORT).show()

            }
            else -> {
                //primeiro chamar viewModel pro viewModel chamar a função abaixo
              viewModel.updatePermissionStatus(false)
            }
        }
    }

//    private fun isPermissionGranted(): Boolean {
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            return false
//        }
//        return true
//    }

    private fun requestPermissions() {
      requestPermissionLauncher.launch(ACCESS_COARSE_LOCATION)
    }
}