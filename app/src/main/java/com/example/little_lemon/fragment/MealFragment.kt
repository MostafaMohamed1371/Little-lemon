package com.example.little_lemon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.little_lemon.R
import com.example.little_lemon.activity.MainActivity
import com.example.little_lemon.adapter.ItemMealsAdapter
import com.example.little_lemon.databinding.FragmentMealBinding
import com.example.little_lemon.utils.Helpers
import com.example.little_lemon.viewmodel.HomeViewModel
import io.github.muddz.styleabletoast.StyleableToast
class MealFragment : Fragment() {
    private lateinit var binding:FragmentMealBinding
    private lateinit var  mealMvvm: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealMvvm=(activity as MainActivity).viewModel
       // mealsAdapter= ItemMealsAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMealBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args:MealFragmentArgs by navArgs()
        val atrical=args.artical
        Glide.with(this)
            .load(atrical.image)
            .into(binding.imagebar)
        binding.collapsingToolbar.title=atrical.title
        binding.textCategory.text="Category : ${atrical.category}"
        binding.textPrice.text="Price : ${atrical.price}$"
        binding.description.text=atrical.description
        binding.addCart.setOnClickListener {
            atrical.let {meals->
                if (meals != null) {
                    mealMvvm.upinsertMeals(meals)
                    StyleableToast.makeText(requireActivity(),"Meal saved",R.style.exampleToast).show()
                    Helpers.counter_cart++

                }

            }
        }

    }
}