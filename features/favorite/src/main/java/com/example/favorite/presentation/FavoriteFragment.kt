package com.example.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.data.remote.mapper.StoresMapper
import com.example.data.data.remote.repository.RepositoryImpl
import com.example.data.domain.GetStoreListUseCaseImpl
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.domain.GetFavoriteListUseCaseImpl
import com.example.favorite.domain.mapper.FavoriteStoresMapper
import com.example.favorite.presentation.adapter.FavoriteStoresAdapter

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel = FavoriteViewModel(
        GetFavoriteListUseCaseImpl(
            GetStoreListUseCaseImpl(
                RepositoryImpl(StoresMapper())
            ), FavoriteStoresMapper()
        )
    )

    private lateinit var rvAdapter: FavoriteStoresAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.getFavoriteList()
        observeFavoriteList()
        observeErrorState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        val rvFavoriteStores = binding.rvFavoriteStoresList
        rvAdapter = FavoriteStoresAdapter(requireActivity())
        rvFavoriteStores.adapter = rvAdapter
        rvFavoriteStores.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
    }

    private fun observeFavoriteList() {
        viewModel.storeLiveData.observe(requireActivity(), { list ->
            list?.let {
                rvAdapter.dataSet.clear()
                rvAdapter.dataSet.addAll(list)
                rvAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun observeErrorState() {
        viewModel.errorStateLiveData.observe(requireActivity(), { throwable ->
            throwable?.let {
                Toast.makeText(requireActivity(), "Algo deu errado :(", Toast.LENGTH_SHORT).show()
            }
        })
    }
}