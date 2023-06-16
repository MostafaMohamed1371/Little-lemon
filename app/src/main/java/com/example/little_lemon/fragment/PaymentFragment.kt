package com.example.little_lemon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.little_lemon.R
import com.example.little_lemon.databinding.FragmentPaymentBinding
import com.example.little_lemon.utils.Helpers
import io.github.muddz.styleabletoast.StyleableToast


class PaymentFragment : Fragment() {
    lateinit var binding:FragmentPaymentBinding
//   private var text1:String=""
//    var text2:String?=null
//    var text3:String?=null
//    var text4:String?=null
// Arbitrary number and used only in this activity. Change it as you wish.
val ACCEPT_PAYMENT_REQUEST: Int = 10
    // Replace this with your actual payment key
    final var paymentKey:String ="ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmpiR0Z6Y3lJNklrMWxjbU5vWVc1MElpd2ljSEp2Wm1sc1pWOXdheUk2TnpneE9EUTNMQ0p1WVcxbElqb2lhVzVwZEdsaGJDSjkuRDV5Rm1heGZjaXJOVTYzZ1JqejZRY2VvWHVRMkRnRlRnaWtvSjZEOGZoT0p6S0lfRmlvQ1hJLTE3SWx3U1RWU0Y5RkRuVk1XMm5EdFA4UWxwbXRJOXc="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentPaymentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.PayNow.setOnClickListener {
            numberVisaCard()
        }

    }

    fun numberVisaCard(){
        val text1=binding.editTextTextPersonName1.text.toString().trim()
        Helpers.text1=text1
        val  text2= binding.editTextTextPersonName2.text.toString().trim()
        Helpers.text2=text2
        val text3= binding.editTextTextPersonName3.text.toString().trim()
        Helpers.text3=text3
        val text4= binding.editTextTextPersonName4.text.toString().trim()
        Helpers.text4=text4
        val text5= binding.editTextTextPersonName5.text.toString().trim()
        Helpers.text5=text5
        val text6= binding.editTextTextPersonName6.text.toString().trim()
        Helpers.text6=text6
        val total_text=text1+text2+text3+text4

        if (total_text.length==16&&text5.length==5&&text6.length==4){
            StyleableToast.makeText(requireActivity(),"Success Payment", R.style.exampleToast).show()

        }else{
            StyleableToast.makeText(requireActivity(),"Faild Payment", R.style.exampleToast).show()
        }
    }

    override fun onStart() {
        super.onStart()
        binding.editTextTextPersonName1.setText( Helpers.text1)
        binding.editTextTextPersonName2.setText( Helpers.text2)
        binding.editTextTextPersonName3.setText( Helpers.text3)
        binding.editTextTextPersonName4.setText( Helpers.text4)
        binding.editTextTextPersonName5.setText( Helpers.text5)
        binding.editTextTextPersonName6.setText( Helpers.text6)

    }



}