package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentOtpVerifiedBinding
import com.paul.groceryapp.databinding.FragmentSplashBinding


class OtpVerifiedFragment : Fragment() {
    private var _binding : FragmentOtpVerifiedBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // val view = inflater.inflate(R.layout.fragment_otp_verified, container, false)
        _binding = FragmentOtpVerifiedBinding.inflate(inflater,container,false)
        binding.browseHome.setOnClickListener {
            findNavController().navigate(R.id.action_otpVerifiedFragment_to_loginPageFragment)
        }
        return binding.root
    }


}