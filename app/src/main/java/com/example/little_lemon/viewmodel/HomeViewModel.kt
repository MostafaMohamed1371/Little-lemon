package com.example.little_lemon.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.little_lemon.repositry.MealsRepsoitry
import com.example.little_lemon.retrofit.MealInstance
import com.example.little_lemon.retrofit.Menu
import com.example.little_lemon.retrofit.meals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val mealsRepositry: MealsRepsoitry
): ViewModel()  {
    private var randomMealLiveData= MutableLiveData<Menu?>()
    private var categoryMealsLiveData= MutableLiveData<List<Menu?>>()
    private var mealsLiveData= MutableLiveData<List<Menu?>>()
//    private var countLiveData= MutableLiveData<Int>()
    private  var cartLiveData=mealsRepositry.db.getMealDao().getAllMeals()
    fun getRandomMeal() {
        viewModelScope.launch {
            mealsRepositry.getMeals().enqueue(object : retrofit2.Callback<meals> {
                override fun onResponse(call: Call<meals>, response: Response<meals>) {
                    if (response.body() != null) {
                        val randomMeal: Menu? = response.body()!!.menu?.get(0)
                        randomMealLiveData.value = randomMeal

                    }
                }

                override fun onFailure(call: Call<meals>, t: Throwable) {
                    Log.d("HomeFragment", t.message.toString())
                }

            })
        }
    }
    fun getMealsByCategory() {
        viewModelScope.launch {
            mealsRepositry.getMeals().enqueue(object : retrofit2.Callback<meals> {
                override fun onResponse(call: Call<meals>, response: Response<meals>) {
                    if (response.body() != null) {
                        categoryMealsLiveData.value = response.body()!!.menu!!
                    }
                }

                override fun onFailure(call: Call<meals>, t: Throwable) {
                    Log.d("HomeFragment", t.message.toString())
                }

            })


        }
    }

    fun getMeals() {
        viewModelScope.launch {
            mealsRepositry.getMeals().enqueue(object : retrofit2.Callback<meals> {
                override fun onResponse(call: Call<meals>, response: Response<meals>) {
                    if (response.body() != null) {
                        mealsLiveData.value = response.body()!!.menu!!
                    }
                }

                override fun onFailure(call: Call<meals>, t: Throwable) {
                    Log.d("HomeFragment", t.message.toString())
                }

            })


        }
    }


    fun observeRandomMealLiveData(): LiveData<Menu?> {
        return randomMealLiveData
    }

    fun observeCategoryMealsLiveData(): LiveData<List<Menu?>> {
        return categoryMealsLiveData
    }

    fun observeMealsLiveData(): LiveData<List<Menu?>> {
        return mealsLiveData
    }

    fun upinsertMeals(meals: Menu){
        viewModelScope.launch {
            mealsRepositry.db.getMealDao().upsert(meals)
        }
    }

    fun deleteMeals(meals: Menu){
        viewModelScope.launch {
            mealsRepositry.db.getMealDao().delete(meals)
        }
    }

    fun observeCartLiveData(): LiveData<List<Menu>> {
        return cartLiveData
    }

//    fun observeCountLiveData(): LiveData<Int> {
//        return countLiveData
//    }
}