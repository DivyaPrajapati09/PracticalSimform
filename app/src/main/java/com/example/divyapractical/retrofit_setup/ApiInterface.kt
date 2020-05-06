package com.example.divyapractical.retrofit_setup

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    fun getUserList(@Query("results") noOfResult : Int) : Call<UserModel.UserResponse>

}