package com.example.little_lemon.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.little_lemon.R
import com.example.little_lemon.databinding.ActivityMainBinding
import com.example.little_lemon.databinding.FragmentCheckOutBinding
import com.example.little_lemon.payment.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckOutFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    lateinit var binding: FragmentCheckOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentCheckOutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Button1.setOnClickListener { getToken() }

        mainViewModel.move.observe(requireActivity())
        {
            if(it == true)
            {
                //startActivity(Intent(this,IframeActivity::class.java))
                findNavController().navigate(R.id.action_checkOutFragment_to_detailsCardFragment)
            }
        }
    }
    private fun getToken() {
        mainViewModel.getToken()
    }
}