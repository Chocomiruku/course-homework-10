package com.chocomiruku.homework10.domain

class CheckIsAddedToFavouritesUseCase(private val repository: FishRepository) {
    fun execute(fish: Fish): Boolean {
        return repository.isAddedToFavourites(fish)
    }
}