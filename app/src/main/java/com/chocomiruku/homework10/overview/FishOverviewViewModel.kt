package com.chocomiruku.homework10.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.repository.FishRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class FishOverviewViewModel : ViewModel() {
    private val fishRepository = FishRepository()

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
                _fish.value = fishRepository.refreshFish()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                _fish.value = ArrayList()
            }
        }
    }
}