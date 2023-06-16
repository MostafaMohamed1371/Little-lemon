package com.example.little_lemon.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.little_lemon.R
import com.example.little_lemon.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding:FragmentSplashBinding
    @Inject
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // mAuth= FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSplash.setOnClickListener {
            binding.progressBarSplash.visibility=View.VISIBLE
            if (mAuth.currentUser!=null){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
               // val intent= Intent(this,HomeActivity::class.java)
                //startActivity(intent)
               // finish()
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
              //  val intent= Intent(this,LoginActivity::class.java)
              //  startActivity(intent)
              //  finish()

            }

        }

    }


}