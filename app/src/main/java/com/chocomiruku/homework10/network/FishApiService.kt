package com.chocomiruku.homework10.network

import com.chocomiruku.homework10.domain.Fish
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.fishwatch.gov/api/"

private val moshi = Moshi.Builder()
    .add(FishJsonAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()


interface FishApiService {
    @GET("species")
    fun getFishAsync(): Deferred<List<Fish>>
}


object FishApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val retrofitService: FishApiService = retrofit.create(FishApiService::class.java)
}