package com.itelligent.ldfandroidproject.data.repository

import com.itelligent.ldfandroidproject.data.ApiService
import com.itelligent.ldfandroidproject.data.model.UserResponse
import retrofit2.Response
import javax.inject.Inject

class UserRepo @Inject constructor(
    private val apiService: ApiService
)
{
    suspend fun getUsers(): Response<ArrayList<UserResponse>> = apiService.getUsers()
}