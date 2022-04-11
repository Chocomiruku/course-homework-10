package com.chocomiruku.homework10.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocomiruku.homework10.domain.CheckIsAddedToFavouritesUseCase
import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.domain.RefreshFishUseCase
import com.chocomiruku.homework10.domain.UpdateFavouritesUseCase
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class FishOverviewViewModel(
    private val refreshFishUseCase: RefreshFishUseCase,
    private val updateFavouritesUseCase: UpdateFavouritesUseCase,
    private val checkIsAddedToFavouritesUseCase: CheckIsAddedToFavouritesUseCase
) : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _fish = MutableLiveData<List<Fish>?>()
    val fish: LiveData<List<Fish>?>
        get() = _fish

    init {
        getFish()
    }

    private fun getFish() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _fish.value = refreshFishUseCase.execute()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _fish.value = ArrayList()
            }
        }
    }

    fun updateFavourites(fish: Fish) {
        updateFavouritesUseCase.execute(fish)
    }

    fun checkIsAddedToFavourites(fish: Fish): Boolean {
        return checkIsAddedToFavouritesUseCase.execute(fish)
    }
}