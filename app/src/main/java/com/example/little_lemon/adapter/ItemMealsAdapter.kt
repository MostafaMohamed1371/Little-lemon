package com.example.little_lemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.little_lemon.databinding.ItemMealBinding
import com.example.little_lemon.retrofit.Menu

class ItemMealsAdapter : RecyclerView.Adapter<ItemMealsAdapter.MyviewHolder>(){
    private var mealsList=ArrayList<Menu>()
    lateinit var onItemClickAddCart:((Menu)->Unit)
    lateinit var onItemClickMeal:((Menu)->Unit)
    lateinit var context: Context
    fun setMeals(categoriesList:ArrayList<Menu>){
        this.mealsList=categoriesList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(ItemMealBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].image)
            .into(holder.binding.imageMeal)
        holder.binding.category.text=mealsList[position].category
        holder.binding.title.text=mealsList[position].title
        holder.binding.description.text=mealsList[position].description
        holder.binding.price.text= mealsList[position].price+"\$"
//        holder.itemView.setOnClickListener {
//            onItemClick.invoke(mealsList[position])
//        }
        holder.binding.addCart.setOnClickListener {
            onItemClickAddCart.invoke(mealsList[position])

        }
        holder.itemView.setOnClickListener {
            onItemClickMeal.invoke(mealsList[position])
        }


    }


    override fun getItemCount(): Int {
        return mealsList.size
    }

    class  MyviewHolder(var binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

}




