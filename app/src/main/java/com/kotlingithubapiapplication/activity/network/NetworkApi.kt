package com.kotlingithubapiapplication.activity.network

import com.kotlingithubapiapplication.activity.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkApi {

    @GET("users/{user}/repos")
    fun getUserRepo(@Path("user") repoName: String): Call<List<Repository>>

}