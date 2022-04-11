package com.chocomiruku.homework10.domain

class UpdateFavouritesUseCase(private val repository: FishRepository) {
    fun execute(fish: Fish) {
        repository.updateFavourites(fish)
    }
}