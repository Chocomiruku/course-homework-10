package com.chocomiruku.homework10.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chocomiruku.homework10.databinding.FragmentFishOverviewBinding

class FishOverviewFragment : Fragment() {
    private var _binding: FragmentFishOverviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FishOverviewViewModel by lazy {
        ViewModelProvider(
            this,
            FishOverviewViewModelFactory(requireContext())
        ).get(
            FishOverviewViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFishOverviewBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.factsList.adapter = FishAdapter(FishAdapter.OnClickListener {
            viewModel.updateFavourites(it)
        }, viewModel)


        return binding.root
    }
}