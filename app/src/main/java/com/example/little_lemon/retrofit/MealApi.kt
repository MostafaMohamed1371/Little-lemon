package com.example.little_lemon.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET("menu.json")
    fun getRandamMeal(): Call<meals>
}