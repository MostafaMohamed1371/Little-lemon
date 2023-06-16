package com.example.little_lemon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.little_lemon.R
import com.example.little_lemon.activity.MainActivity
import com.example.little_lemon.adapter.ItemMealsAdapter
import com.example.little_lemon.databinding.FragmentClickOnCategoryBinding
import com.example.little_lemon.retrofit.Menu
import com.example.little_lemon.viewmodel.HomeViewModel
import io.github.muddz.styleabletoast.StyleableToast


class ClickOnCategoryFragment : Fragment() {
    private lateinit var binding: FragmentClickOnCategoryBinding
    private lateinit var  clickOnCategoryMvvm: HomeViewModel
    private lateinit var mealsAdapter: ItemMealsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickOnCategoryMvvm=(activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentClickOnCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareMealsItemsRecyclerView()
        clickOnCategoryMvvm.getMealsByCategory()
        observerOnCategory()
        onMealItemClick()
        onAddCartClich()

    }

    private fun prepareMealsItemsRecyclerView() {
        mealsAdapter= ItemMealsAdapter()
        binding.RecyclerViewOnCategory.apply {
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter=mealsAdapter
        }
    }

    private fun observerOnCategory() {
        clickOnCategoryMvvm.observeCategoryMealsLiveData().observe(viewLifecycleOwner,object :
            Observer<List<Menu?>> {

            override fun onChanged(t: List<Menu?>) {
                mealsAdapter.setMeals(t as ArrayList<Menu>)

            }

        })
    }

    private fun onMealItemClick() {
        mealsAdapter.onItemClickMeal={
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_clickOnCategoryFragment_to_mealFragment,bundle)
        }

    }

    private fun onAddCartClich() {
        mealsAdapter.onItemClickAddCart={
                meal->
            if (meal != null) {
                clickOnCategoryMvvm.upinsertMeals(meal)
                StyleableToast.makeText(requireActivity(),"Meal Saved", R.style.exampleToast).show()
                //Toast.makeText(this,"Meal Saved",Toast.LENGTH_SHORT).show()

            }
            //   StyleableToast.makeText(requireActivity(),"success", R.style.exampleToast).show()

        }
    }

}