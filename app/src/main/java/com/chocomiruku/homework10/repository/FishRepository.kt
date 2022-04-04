package com.chocomiruku.homework10.repository

import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.network.FishApi

class FishRepository {
    suspend fun refreshFish(): List<Fish> {
        return FishApi.retrofitService.getFishAsync().await()
    }
}