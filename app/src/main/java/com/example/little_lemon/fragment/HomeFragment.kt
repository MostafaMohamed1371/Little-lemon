package com.example.little_lemon.fragment
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.little_lemon.activity.MainActivity
import com.example.little_lemon.R
import com.example.little_lemon.adapter.CategoryAdapter
import com.example.little_lemon.adapter.ItemMealsAdapter
import com.example.little_lemon.databinding.FragmentHomeBinding
import com.example.little_lemon.retrofit.Menu
import com.example.little_lemon.utils.Helpers
import com.example.little_lemon.viewmodel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.File
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
     @Inject
     @Named("imageStorage")
     lateinit var imageStorage: StorageReference
    private lateinit var  homeMvvm: HomeViewModel
  // private val homeMvvm: HomeViewModel by viewModels()
  // private var homeMvvm= hiltViewModel<HomeViewModel>()
    private lateinit var randamMeal:Menu
   // private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var mealsAdapter: ItemMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        imageStorage=
//            FirebaseStorage.getInstance().getReferenceFromUrl("gs://little-lemon-12c55.appspot.com/").child(
//                FirebaseAuth.getInstance().currentUser!!.uid)
               //  homeMvvm=ViewModelProvider(this)[HomeViewModel::class.java]
                 homeMvvm=(activity as MainActivity).viewModel




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toProfile.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

        }

        getImageFromFirebase()
        homeMvvm.getRandomMeal()
        observerRandomMeal()
      //  prepareCategoryItemsRecyclerView()
        prepareMealsItemsRecyclerView()
        homeMvvm.getMealsByCategory()
        observerCategory()
        binding.imageCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }
        observerMeals()
        onAddCartClich()
        onMealItemClick()
       // onCategoryItemClick()
        onTabSelected()

    }

    private fun observerMeals() {
        homeMvvm.observeMealsLiveData().observe(viewLifecycleOwner,object :Observer<List<Menu?>>{

            override fun onChanged(t: List<Menu?>) {
                //  categoryAdapter.setCategories(t as ArrayList<Menu>)
                mealsAdapter.setMeals(t as ArrayList<Menu>)


            }

        })
    }


    private fun observerCategory() {
        homeMvvm.observeCategoryMealsLiveData().observe(viewLifecycleOwner,object :Observer<List<Menu?>>{

            override fun onChanged(t: List<Menu?>) {
              //  categoryAdapter.setCategories(t as ArrayList<Menu>)
              //  mealsAdapter.setMeals(t as ArrayList<Menu>)
                addSourceTab(t)

            }

        })
    }



    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner,object : Observer<Menu?> {
            override fun onChanged(t: Menu?) {

                Glide.with(this@HomeFragment)
                    .load(t!!.image)
                    .into(binding.imageMeal)
                binding.title.text=t.title
                binding.description.text=t.description
                binding.category.text=t.category
                this@HomeFragment.randamMeal=t


            }

        })
    }

    private  fun  getImageFromFirebase() {
        val file: File = File.createTempFile("image","jpeg")
        imageStorage.getFile(file).addOnSuccessListener {
            val  bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
            to_profile.setImageBitmap(bitmap)
        }.addOnFailureListener {
            StyleableToast.makeText(requireActivity(),"Image failed to load",
               R.style.exampleToast).show()
        }
    }

//    private fun prepareCategoryItemsRecyclerView() {
//        categoryAdapter= CategoryAdapter()
//        binding.categoriesRecyclerView.apply {
//            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
//            adapter=categoryAdapter
//        }
//    }
    private fun prepareMealsItemsRecyclerView() {
        mealsAdapter= ItemMealsAdapter()
        binding.itemMeal.apply {
            layoutManager= LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            adapter=mealsAdapter
        }
    }
    private fun onAddCartClich() {
        mealsAdapter.onItemClickAddCart={
                meal->
            if (meal != null) {
                homeMvvm.upinsertMeals(meal)
                StyleableToast.makeText(requireActivity(),"Meal Saved", R.style.exampleToast).show()
                Helpers.counter_cart++
                binding.counterCart.text=Helpers.counter_cart.toString()
                //Toast.makeText(this,"Meal Saved",Toast.LENGTH_SHORT).show()

            }
         //   StyleableToast.makeText(requireActivity(),"success", R.style.exampleToast).show()

        }
    }
    private fun onMealItemClick() {
        mealsAdapter.onItemClickMeal={
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_mealFragment,bundle)
        }

    }
//    private fun onCategoryItemClick() {
//        categoryAdapter.onItemClick={
//            val bundle=Bundle().apply {
//                putSerializable("artical",it.category)
//            }
//            findNavController().navigate(R.id.action_homeFragment_to_clickOnCategoryFragment,bundle)
//        }
//
//    }

    // when tab selected what will show
    private fun onTabSelected() {
        binding.tableLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                   val  source = tab?.tag as Menu
//                    val bundle=Bundle().apply {
//                        putSerializable("artical",source)
//                    }
                  //  findNavController().navigate(R.id.action_homeFragment_to_clickOnCategoryFragment,bundle)

                  //  StyleableToast.makeText(requireActivity(),source.title, R.style.exampleToast).show()

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    homeMvvm.getMeals()

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            }
        )

    }

    // add tab in tab Layout
    private fun addSourceTab(sources: List<Menu?>?) {
        sources?.forEach { source ->
            val tab = binding.tableLayout.newTab()
            tab.text = source?.category
            tab.tag = source
            binding.tableLayout.addTab(tab)

        }
    }

    override fun onStart() {
        super.onStart()
        binding.counterCart.text=Helpers.counter_cart.toString()

    }


}