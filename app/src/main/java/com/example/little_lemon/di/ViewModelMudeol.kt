package com.example.little_lemon.di

import com.example.little_lemon.repositry.MealsRepsoitry
import com.example.little_lemon.retrofit.MealApi
import com.example.little_lemon.roomdb.MealDao
import com.example.little_lemon.roomdb.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ViewModelMudeol {


    @Provides
    @Singleton
     fun provideGetRepositry(db:MealDatabase,mealApi: MealApi): MealsRepsoitry{
        return MealsRepsoitry(db,mealApi)
    }
}