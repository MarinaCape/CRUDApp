package com.marina.data.cloud

import com.marina.domain.dto.user.User
import com.marina.domain.request.UserRequest
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    companion object {
        const val USER = "User"
    }

    @GET(USER)
    suspend fun getUsers(): ArrayList<User>

    @DELETE("$USER/{id}")
    suspend fun deleteUser(@Path("id") idUser: String): Response<Unit>

    @POST("$USER")
    suspend fun insertUser(@Body user: UserRequest): Response<Unit>

    @PUT("$USER")
    suspend fun modifyUser(@Body user: UserRequest): Response<Unit>
}