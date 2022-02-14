package com.example.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.databinding.FragmentStandardBinding
import com.example.favorite.presentation.adapter.FavoriteStoresAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val binding: FragmentStandardBinding by lazy {
        FragmentStandardBinding.inflate(layoutInflater)
    }

    private val rvAdapter: FavoriteStoresAdapter by lazy {
        FavoriteStoresAdapter()
    }

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupListeners()

        observeState()
        viewModel.getFavoriteList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteList()
    }

    private fun setupRecyclerView() {
        val rvFavoriteStores = binding.rvStoresList
        rvFavoriteStores.adapter = rvAdapter
        rvFavoriteStores.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setupListeners() {
        binding.layoutError.btnError.setOnClickListener {
            onClickBtnTryAgain()
        }
    }

    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->

            with(state) {
                binding.rvStoresList.isVisible = favoriteList?.isNotEmpty() ?: false
                binding.txtEmptyResult.isVisible = favoriteList?.isEmpty() ?: false
                binding.layoutError.root.isVisible = isErrorVisible
                binding.progressBar.isVisible = isLoadingVisible

                favoriteList?.let {
                    rvAdapter.dataSet.clear()
                    rvAdapter.dataSet.addAll(it)
                    rvAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun onClickBtnTryAgain() {
        viewModel.tryAgain()
    }
}
    
