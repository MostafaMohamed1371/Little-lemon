package com.example.little_lemon.di


import com.example.little_lemon.payment.ApiInterface
import com.example.little_lemon.retrofit.MealApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val BASE_URL1 = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/"
const val BASE_URL2 = "https://accept.paymobsolutions.com/api/"
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModueol {

    @Provides
    @Singleton
    fun provideHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            . retryOnConnectionFailure(true)
            .readTimeout(30, TimeUnit.SECONDS)
            . connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit): MealApi {
        return retrofit.create(MealApi::class.java)
    }


    @Provides
    @Singleton
    @Named("provideRetrofit")
    fun provideRetrofitPayMob(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService (@Named("provideRetrofit") retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}