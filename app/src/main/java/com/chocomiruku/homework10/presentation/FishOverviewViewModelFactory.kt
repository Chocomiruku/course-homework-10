package com.chocomiruku.homework10.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chocomiruku.homework10.domain.CheckIsAddedToFavouritesUseCase
import com.chocomiruku.homework10.domain.RefreshFishUseCase
import com.chocomiruku.homework10.domain.UpdateFavouritesUseCase
import com.chocomiruku.homework10.model.FishRepositoryImpl

class FishOverviewViewModelFactory(
    context: Context
) :
    ViewModelProvider.Factory {

    private val fishRepository = FishRepositoryImpl(context)
    private val refreshFishUseCase = RefreshFishUseCase(fishRepository)
    private val updateFavouritesUseCase = UpdateFavouritesUseCase(fishRepository)
    private val checkIsAddedToFavouritesUseCase = CheckIsAddedToFavouritesUseCase(fishRepository)

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishOverviewViewModel::class.java)) {
            return FishOverviewViewModel(
                refreshFishUseCase,
                updateFavouritesUseCase,
                checkIsAddedToFavouritesUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}