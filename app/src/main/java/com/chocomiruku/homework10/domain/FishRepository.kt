package com.chocomiruku.homework10.domain

interface FishRepository {
    suspend fun refreshFish(): List<Fish>
    fun updateFavourites(fish: Fish)
    fun isAddedToFavourites(fish: Fish): Boolean
}