package com.example.nearby.presentation

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.databinding.FragmentStandardBinding
import com.example.nearby.R
import com.example.nearby.presentation.adapter.NearbyStoresAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class NearbyFragment : Fragment() {

    private val binding: FragmentStandardBinding by lazy {
        FragmentStandardBinding.inflate(layoutInflater)
    }

    private val rvAdapter: NearbyStoresAdapter by lazy {
        NearbyStoresAdapter()
    }

    private val viewModel: NearbyViewModel by viewModel()

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val isGranted =
                permissions[coarseLocationPermission] ?: false && permissions[fineLocationPermission] ?: false

            viewModel.updatePermissionStatus(isGranted)
            if (!isGranted) {
                showLocationPermissionRequestToast()
            }
        }

    private fun showLocationPermissionRequestToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.request_location_permission_toast),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION))
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

        requestPermissions()
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

    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->
            with(state) {
                binding.apply {
                    rvStoresList.isVisible = nearbyList?.isNotEmpty() ?: false
                    txtEmptyResult.isVisible = nearbyList?.isEmpty() ?: false
                    layoutError.root.isVisible = isErrorVisible
                    progressBar.isVisible = isLoadingVisible
                }
                nearbyList?.let {
                    rvAdapter.updateList(it)
                }
            }
        })
    }
}