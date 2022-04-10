package com.chocomiruku.homework10.domain

class RefreshFishUseCase(private val repository: FishRepository) {
    suspend fun execute(): List<Fish> {
        return repository.refreshFish()
    }
}