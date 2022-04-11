package com.chocomiruku.homework10.model

import android.content.Context
import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.domain.FishRepository
import com.chocomiruku.homework10.model.network.FishApi

class FishRepositoryImpl(context: Context) : FishRepository {
    private val sharedPreferences =
        context.getSharedPreferences("favourites_ids", Context.MODE_PRIVATE)

    override suspend fun refreshFish(): List<Fish> {
        return FishApi.retrofitService.getFishAsync().await()
    }

    override fun updateFavourites(fish: Fish) {
        if (isAddedToFavourites(fish)) {
            deleteFromFavourites(fish)
        } else addToFavourites(fish)
    }

    private fun addToFavourites(fish: Fish) {
        sharedPreferences.edit().putBoolean(fish.speciesName, true).apply()
    }

    private fun deleteFromFavourites(fish: Fish) {
        sharedPreferences.edit().remove(fish.speciesName).apply()
    }

    override fun isAddedToFavourites(fish: Fish): Boolean {
        return sharedPreferences.getBoolean(fish.speciesName, false)
    }
}