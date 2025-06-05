package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentLoginPageBinding
import com.paul.groceryapp.databinding.FragmentOtpBinding


class OtpFragment : Fragment() {

    private var _binding : FragmentOtpBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOtpBinding.inflate(inflater,container,false)
        binding.verifyTextView.setOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_otpVerifiedFragment)
        }
        return binding.root
    }


}