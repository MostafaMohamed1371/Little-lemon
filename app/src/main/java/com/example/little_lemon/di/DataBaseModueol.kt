package com.example.little_lemon.di
import android.content.Context
import androidx.room.Room
import com.example.little_lemon.R
import com.example.little_lemon.adapter.ItemMealsAdapter
import com.example.little_lemon.repositry.MealsRepsoitry
import com.example.little_lemon.retrofit.MealApi
import com.example.little_lemon.roomdb.MealDao
import com.example.little_lemon.roomdb.MealDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.muddz.styleabletoast.StyleableToast
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataBaseModueol {

//    @Binds
//    @Singleton
//   abstract fun provideGetDataBase(
//        db:MealDatabase
//   ): MealsRepsoitry


    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext context: Context): MealDatabase {
        return Room.databaseBuilder(
            context,
            MealDatabase::class.java,
            "MyNewsDatabase",
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideMealDao(dataBaseManager: MealDatabase): MealDao {
        return dataBaseManager.getMealDao()
    }


}