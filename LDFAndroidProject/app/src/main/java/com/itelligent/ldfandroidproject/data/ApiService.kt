package com.itelligent.ldfandroidproject.data

import com.itelligent.ldfandroidproject.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("users")
    suspend fun getUsers(): Response<ArrayList<UserResponse>>
}