package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentLoginBinding
import com.paul.groceryapp.databinding.FragmentSplashBinding


class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater,container, false)
      binding.loginText.setOnClickListener{
          findNavController().navigate(R.id.action_loginFragment_to_loginPageFragment)
      }

        return binding.root
    }


}