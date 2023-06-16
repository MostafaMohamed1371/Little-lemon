package com.example.little_lemon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.little_lemon.R
import com.example.little_lemon.activity.MainActivity
import com.example.little_lemon.adapter.CartAdapter
import com.example.little_lemon.databinding.FragmentCartBinding
import com.example.little_lemon.retrofit.Menu
import com.example.little_lemon.utils.Helpers
import com.example.little_lemon.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar



class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    private lateinit var cartMvvm: HomeViewModel
    private lateinit var CartAdapter: CartAdapter
   // private var count: Int = 1
  //  private var totalPrice: Int=0
    private var deliveryPrice: Int=10
    var subPrice:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartMvvm=(activity as MainActivity).viewModel


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemTouchHelper=object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val meals=CartAdapter.differ.currentList[position]
                cartMvvm.deleteMeals(meals)
                Helpers.counter_cart--
                Snackbar.make(view,"meal deleted", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo")
                    {
                        cartMvvm.upinsertMeals(meals)
                        Helpers.counter_cart++

                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.recyclerViewSave)
        PrepareAdapter()
        observeCartLiveData()
        binding.Checkout.setOnClickListener {
            findNavController().navigate(R.id.action_cartFragment_to_checkOutFragment)
        }
      //  onItemClichPlus()
       // onItemClichMinus()
        //observeCountLiveData()
        //checkout()
    }

    private fun observeCartLiveData() {
        cartMvvm.observeCartLiveData().observe(viewLifecycleOwner, Observer{
                cartList->CartAdapter.differ.submitList(cartList)

        })
    }

    /*

    private fun observeCountLiveData(){
        cartMvvm.observeCountLiveData().observe(viewLifecycleOwner,{count->
            CartAdapter.onItemClickPlus={
                count += 1
                binding.root.Number.text= count.toString()
                totalPrice=count * it.price!!.toInt()
                binding.root.total_price.text="Total price : "+totalPrice+"\$"
                binding.priceSubTotal.text=totalPrice.toString()+"\$"
                binding.priceTotal.text=(totalPrice + deliveryPrice).toString()+"\$"

            }

            CartAdapter.onItemClickMinus={
                if (count >= 2) {
                    this.count -= 1
                    binding.root.Number.text = count.toString()
                    totalPrice=count * it.price!!.toInt()
                    binding.root.total_price.text="Total price : "+totalPrice+"\$"
                    binding.priceSubTotal.text=totalPrice.toString()+"\$"
                    binding.priceTotal.text=(totalPrice + deliveryPrice).toString()+"\$"
                }
            }

        })
    }
*/

    private fun PrepareAdapter() {
        CartAdapter= CartAdapter()
        binding.recyclerViewSave.apply {
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter=CartAdapter



        }
        CartAdapter.onImagePlusCLickListener=object :CartAdapter.OnClickListener{
            override fun onItemClicked(item: Menu?, position: Int) {
                //val selectItem=CartAdapter.differ.currentList[position].total_Price
                 subPrice += item!!.price!!.toInt()
                 Helpers.supTotalPrice=subPrice
                binding.priceSubTotal.text=subPrice.toString()+"\$"
                binding.priceTotal.text=(subPrice + deliveryPrice).toString()+"\$"
                Helpers.finalTotal=subPrice + deliveryPrice

            }

        }


        CartAdapter.onImageMinusCLickListener=object :CartAdapter.OnClickListener {
            override fun onItemClicked(item: Menu?, position: Int) {
                if (item!!.counter > 0) {
                   return


                }else{
                    subPrice -= item!!.price!!.toInt()
                    Helpers.supTotalPrice = subPrice
                    binding.priceSubTotal.text = subPrice.toString() + "\$"
                    binding.priceTotal.text = (subPrice + deliveryPrice).toString() + "\$"
                    Helpers.finalTotal=subPrice + deliveryPrice
                }


            }
        }



    }

/*
    private fun onItemClichPlus() {
        CartAdapter.onItemClickPlus={
           it.counter += 1
            Log.e("aaaa",it.counter.toString())
            binding.root.Number.text= it.counter.toString()
            totalPrice=it.counter * it.price!!.toInt()
            binding.root.total_price.text="Total price : "+totalPrice+"\$"
           binding.priceSubTotal.text="$totalPrice"
            Log.e("aaaa",totalPrice.toString())
            binding.priceTotal.text=(totalPrice + deliveryPrice).toString()+"\$"

        }
   }
    private fun onItemClichMinus() {
        CartAdapter.onItemClickMinus={
            if (it.counter >= 2) {
                it.counter -= 1
                binding.root.Number.text = it.counter.toString()
                totalPrice=it.counter * it.price!!.toInt()
                binding.root.total_price.text="Total price : "+totalPrice+"\$"
                binding.priceSubTotal.text=binding.root.total_price.toString()+"\$"
                binding.priceTotal.text=(totalPrice + deliveryPrice).toString()+"\$"
            }
        }
    }
    private fun checkout(){
        binding.priceSubTotal.text="${totalPrice}\$"
        binding.priceDelivere.text=deliveryPrice.toString()+"\$"
        binding.priceTotal.text=(totalPrice!!.toInt() + deliveryPrice.toInt()).toString()+"\$"
    }

*/
    override fun onStart() {
        super.onStart()
    binding.priceSubTotal.text=Helpers.supTotalPrice.toString()+"\$"
    binding.priceTotal.text=(Helpers.supTotalPrice + deliveryPrice).toString()+"\$"
    Helpers.finalTotal=Helpers.supTotalPrice  + deliveryPrice
    }
}




