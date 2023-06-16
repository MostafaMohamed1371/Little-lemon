package com.example.little_lemon.repositry

import com.example.little_lemon.retrofit.MealApi
import com.example.little_lemon.roomdb.MealDatabase
import javax.inject.Inject


class MealsRepsoitry @Inject constructor (
    val db:MealDatabase,val mealApi:MealApi
) {
    suspend fun getMeals()= mealApi.getRandamMeal()


}