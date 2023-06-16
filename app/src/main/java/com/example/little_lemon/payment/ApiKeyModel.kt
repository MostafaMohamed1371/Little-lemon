package com.example.little_lemon.payment
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiKeyModel(
    @SerializedName("api_key")
    @Expose
    val api_key:String)