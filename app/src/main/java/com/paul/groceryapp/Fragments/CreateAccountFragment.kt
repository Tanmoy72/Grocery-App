package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentCreateAccountBinding
import com.paul.groceryapp.databinding.FragmentSplashBinding


class CreateAccountFragment : Fragment() {

    private var _binding : FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateAccountBinding.inflate(inflater,container, false)
        binding.emailOrPhoneTextView.setOnClickListener{
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
        }

        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_signUpFragment)
        }
        return binding.root
    }


}