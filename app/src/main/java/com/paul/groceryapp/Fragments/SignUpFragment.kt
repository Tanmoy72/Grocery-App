package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.paul.groceryapp.R

import com.paul.groceryapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        binding.signUp.setOnClickListener {
            signUpUser()
        }

        binding.goToLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginPageFragment)
        }


        return binding.root
    }

    private fun signUpUser() {
        val name = binding.nameEditTextSignup.text.toString().trim()
        val phone = binding.phoneEditTextSignup.text.toString().trim()
        val password = binding.passwordEditTextSignUp.text.toString().trim()
        val confirmPassword = binding.conformEditTextSignUp.text.toString().trim()

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }
        if (!phone.matches(Regex("^[0-9]{10}$"))) {
            Toast.makeText(context, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(context, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show()
            return
        }

        val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}$")
        if (!password.matches(passwordPattern)) {
            Toast.makeText(
                context,
                "Password must be at least 6 characters and include lowercase, uppercase, digit, and special character.",
                Toast.LENGTH_LONG
            ).show()
            return
        }


        if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val fakeEmail = "$phone@groceryapp.com"

        auth.createUserWithEmailAndPassword(fakeEmail, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = auth.currentUser!!.uid
                    val userMap = mapOf(
                        "name" to name,
                        "phone" to phone
                    )
                    dbRef.child(userId).setValue(userMap)
                    Toast.makeText(context, "SignUp Successful!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_loginPageFragment)
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