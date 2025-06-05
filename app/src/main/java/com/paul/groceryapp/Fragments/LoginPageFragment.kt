package com.paul.groceryapp.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.paul.groceryapp.MainActivity
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentLoginBinding
import com.paul.groceryapp.databinding.FragmentLoginPageBinding


class LoginPageFragment : Fragment() {
    private var _binding : FragmentLoginPageBinding? = null
    private val binding get() = _binding!!


    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginPageBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            loginUser()
        }


        binding.signupTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_signUpFragment)
        }
        return binding.root
    }

    private fun loginUser() {
        val phone = binding.phoneNumLogin.text.toString().trim()
        val password = binding.passwordLogin.text.toString().trim()

        if (phone.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Please enter phone and password", Toast.LENGTH_SHORT).show()
            return
        }

        val fakeEmail = "$phone@groceryapp.com"

        auth.signInWithEmailAndPassword(fakeEmail, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                    // Navigate to home fragment or activity
                    val Intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(Intent)
                } else {
                    Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}