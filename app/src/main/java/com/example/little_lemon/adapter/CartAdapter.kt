package com.example.little_lemon.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.little_lemon.databinding.OrderItemBinding
import com.example.little_lemon.retrofit.Menu
import kotlinx.android.synthetic.main.order_item.view.*

class CartAdapter (): RecyclerView.Adapter<CartAdapter.MyviewHolder>(){
//    lateinit var onItemClickMinus:((Menu)->Unit)
//    lateinit var onItemClickPlus:((Menu)->Unit)
  //  private var count: Int = 1
    // var supTotalPrice:Int=0

    private val diffUtil=object : DiffUtil.ItemCallback<Menu>(){

        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {

            return oldItem.id==newItem.id

        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
      //  holder.bind(differ.currentList[position],onImagePlusCLickListener,onImageMinusCLickListener,position)
        var currentItem=differ.currentList[position]
        Glide.with(holder.itemView)
            .load(differ.currentList[position].image)
            .into(holder.binding.imageMeal)
        holder.binding.category.text=differ.currentList[position].category
        holder.binding.title.text=differ.currentList[position].title
        holder.binding.description.text=differ.currentList[position].description
        holder.binding.price.text="Price : "+differ.currentList[position].price+"\$"
        holder.binding.totalPrice.text="Total price : "+differ.currentList[position].price+"\$"
        holder.binding.Number.text= differ.currentList[position].counter.toString()

        if (onImagePlusCLickListener!=null){

            holder.binding.imagePlus.setOnClickListener {


                    differ.currentList[position].counter += 1
                    holder.binding.Number.text = differ.currentList[position].counter.toString()
                    differ.currentList[position].total_Price =
                        differ.currentList[position].counter * differ.currentList[position].price!!.toInt()
                    holder.binding.totalPrice.text =
                        "Total price : " + differ.currentList[position].total_Price + "\$"
                    Log.e("aaaa", differ.currentList[position].total_Price.toString())
                    // supTotalPrice+= differ.currentList[position].total_Price


                    onImagePlusCLickListener.onItemClicked(currentItem, position)

            }


        }

        if (onImageMinusCLickListener!=null){
            holder.binding.imageMinus.setOnClickListener {
                //  onItemClickMinus.invoke(differ.currentList[position])
                if (differ.currentList[position].counter >= 1) {
                    differ.currentList[position].counter -= 1
                    holder.binding.root.Number.text = differ.currentList[position].counter.toString()
                    differ.currentList[position].total_Price=differ.currentList[position].counter * differ.currentList[position].price!!.toInt()
                    holder.binding.totalPrice.text="Total price : "+differ.currentList[position].total_Price+"\$"
                }
                onImageMinusCLickListener.onItemClicked(currentItem,position)

            }
        }

/*
        holder.binding.imagePlus.setOnClickListener {
          //  onItemClickPlus.invoke(differ.currentList[position])



        }
        holder.binding.imageMinus.setOnClickListener {
          //  onItemClickMinus.invoke(differ.currentList[position])
            if (differ.currentList[position].counter >= 2) {
                differ.currentList[position].counter -= 1
                holder.binding.root.Number.text = differ.currentList[position].counter.toString()
                differ.currentList[position].total_Price=differ.currentList[position].counter * differ.currentList[position].price!!.toInt()
                holder.binding.totalPrice.text="Total price : "+differ.currentList[position].total_Price+"\$"
            }


        }

 */

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class  MyviewHolder(var binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root){
      fun bind(item: Menu,onImagePlusCLickListener:OnClickListener,onImageMinusCLickListener:OnClickListener,position:Int){
         // var currentItem=differ.currentList[position]
          Glide.with(binding.root.context)
              .load(item.image)
              .into(binding.imageMeal)
          binding.category.text=item.category
          binding.title.text=item.title
          binding.description.text=item.description
          binding.price.text="Price : "+item.price+"\$"
          binding.totalPrice.text="Total price : "+item.price+"\$"
          binding.Number.text= item.counter.toString()

          if (onImagePlusCLickListener!=null){

              binding.imagePlus.setOnClickListener {


                  item.counter += 1
                  binding.Number.text= item.counter.toString()
                  item.total_Price=item.counter * item.price!!.toInt()
                  binding.totalPrice.text="Total price : "+item.total_Price+"\$"
                  Log.e("aaaa",item.total_Price.toString())
                  // supTotalPrice+= item.total_Price


                  onImagePlusCLickListener.onItemClicked(item,position)
              }


          }

          if (onImageMinusCLickListener!=null){
              binding.imageMinus.setOnClickListener {
                  //  onItemClickMinus.invoke(item)
                  if (item.counter >= 2) {
                      item.counter -= 1
                      binding.root.Number.text = item.counter.toString()
                      item.total_Price=item.counter * item.price!!.toInt()
                      binding.totalPrice.text="Total price : "+item.total_Price+"\$"
                  }
                  onImageMinusCLickListener.onItemClicked(item,position)

              }
          }


      }
    }

    lateinit var onImagePlusCLickListener: OnClickListener
    lateinit var onImageMinusCLickListener: OnClickListener
    interface OnClickListener{
        fun onItemClicked(item: Menu?,position: Int)
    }


}

