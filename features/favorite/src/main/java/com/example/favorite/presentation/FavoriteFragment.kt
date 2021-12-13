package com.example.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.adapter.FavoriteStoresAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavoriteFragment : Fragment() {

    private val binding: FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
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

    private fun setupRecyclerView() {
        val rvFavoriteStores = binding.rvFavoriteStoresList
        rvFavoriteStores.adapter = rvAdapter
        rvFavoriteStores.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setupListeners() {
        binding.includeLayoutError.btnError.setOnClickListener {
            onClickBtnTryAgain()
        }
    }

    private fun observeState() {
        viewModel.viewStateLiveData.observe(viewLifecycleOwner, { state ->

            with(state) {
                binding.rvFavoriteStoresList.isVisible = favoriteList?.isNotEmpty() ?: false
                binding.txtEmptyResult.isVisible = favoriteList?.isEmpty() ?: false
                binding.includeLayoutError.root.isVisible = isErrorVisible
                binding.progBar.isVisible = isLoadingVisible

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
    
