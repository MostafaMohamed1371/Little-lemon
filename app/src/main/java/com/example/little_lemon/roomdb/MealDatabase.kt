package com.example.little_lemon.roomdb

import android.content.Context
import androidx.room.*
import com.example.little_lemon.retrofit.Menu



@Database(entities = [Menu::class], version = 7)
@TypeConverters(MealTypeConvertor::class)

abstract class MealDatabase : RoomDatabase(){

    abstract fun getMealDao(): MealDao


//    companion object {
//        private const val DATABASE_NAME = "MyNewsDatabase"
//        @Volatile
//        private var instance: MealDatabase? = null
//        fun getinstance(context: Context): MealDatabase {
//            return instance ?: synchronized(context) { buildDatabase(context).also { instance = it } }
//        }
//
//        fun getinstancewithout(): MealDatabase? {
//            return instance
//
//        }
//
//        private fun buildDatabase(context: Context): MealDatabase {
//            return Room.databaseBuilder(context, MealDatabase::class.java, DATABASE_NAME
//                ).fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                    .build()
//
//        }
//    }
}