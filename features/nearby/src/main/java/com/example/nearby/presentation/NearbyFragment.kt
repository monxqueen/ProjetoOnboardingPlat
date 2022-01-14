package com.example.nearby.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
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
        viewModel._viewStatePermission.observe(viewLifecycleOwner, {
        })

        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->

            with(state) {
                binding.rvStoresList.isVisible = nearbyList?.isNotEmpty() ?: false
                binding.txtEmptyResult.isVisible = nearbyList?.isEmpty() ?: false
                binding.layoutError.root.isVisible = isErrorVisible
                binding.progressBar.isVisible = isLoadingVisible
                nearbyList?.let {
                    rvAdapter.updateList(it)
                }
            }
        })
    }

    //TODO: Mover pra ViewModel


    //TODO: Mover pra ViewModel
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
}