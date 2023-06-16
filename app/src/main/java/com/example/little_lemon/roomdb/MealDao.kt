package com.example.little_lemon.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.little_lemon.retrofit.Menu


@Dao
interface MealDao {

    @Insert(onConflict = REPLACE)
    suspend fun upsert(meal: Menu)
    @Delete
    suspend fun delete(meal: Menu)
    @Query("SELECT * FROM mealInformation")
    fun getAllMeals():LiveData<List<Menu>>

}