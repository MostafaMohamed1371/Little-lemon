package com.example.little_lemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.little_lemon.databinding.CategoryItemsBinding
import com.example.little_lemon.retrofit.Menu

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.MyviewHolder>(){
    private var categoriesList=ArrayList<Menu>()
    lateinit var onItemClick:((Menu)->Unit)
    fun setCategories(categoriesList:ArrayList<Menu>){
        this.categoriesList=categoriesList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(CategoryItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoriesList[position].image)
            .into(holder.binding.imageCategory)
        holder.binding.textCategory.text=categoriesList[position].category
        holder.itemView.setOnClickListener {
            onItemClick.invoke(categoriesList[position])
        }


    }


    override fun getItemCount(): Int {
        return categoriesList.size
    }

    class  MyviewHolder(var binding: CategoryItemsBinding) : RecyclerView.ViewHolder(binding.root)

}




