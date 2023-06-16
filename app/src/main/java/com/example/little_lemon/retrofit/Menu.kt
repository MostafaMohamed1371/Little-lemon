package com.example.little_lemon.retrofit


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "mealInformation")
data class Menu(
    var total_Price:Int=0,
    var counter:Int=0,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    @PrimaryKey
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("title")
    val title: String? = null
): Serializable