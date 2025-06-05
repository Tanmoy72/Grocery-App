package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paul.groceryapp.R
import com.paul.groceryapp.databinding.FragmentMainBinding



class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater,container,false)

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navigateToFragment(HomeFragment())
                    true
                }
                R.id.menu -> {
                    navigateToFragment(MenuFragment())
                    true
                }
                R.id.cart -> {
                    navigateToFragment(CartFragment())
                    true
                }
                R.id.save -> {
                    navigateToFragment(SaveFragment())
                    true
                }
                R.id.profile -> {
                    navigateToFragment(ProfileFragment())
                    true
                }


                else -> false
            }
        }

        navigateToFragment(HomeFragment())
        return binding.root
    }

    private fun navigateToFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(binding.frame.id, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}