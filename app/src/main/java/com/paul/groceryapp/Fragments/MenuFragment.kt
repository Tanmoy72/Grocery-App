package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.CategoryAdapter
import com.paul.groceryapp.Model.CategoryModel
import com.paul.groceryapp.OurNewItemsAdapter
import com.paul.groceryapp.R

class MenuFragment : Fragment() {
    private var categoryItemsList: ArrayList<CategoryModel> = arrayListOf(
        CategoryModel(R.drawable.vegetables,"Vegetables"),
        CategoryModel(R.drawable.fish,"Meat And Fish"),
        CategoryModel(R.drawable.medicine,"Medicine"),
        CategoryModel(R.drawable.baby_stroller,"Baby Care"),
        CategoryModel(R.drawable.stationery,"Office Supplies"),
        CategoryModel(R.drawable.beauty,"Beauty"),
        CategoryModel(R.drawable.gym,"Gym Equipment"),
        CategoryModel(R.drawable.gardening,"Gardening"),
        CategoryModel(R.drawable.pet_care,"Pet Care"),
        CategoryModel(R.drawable.eye_glasses,"Eye Wears"),
        CategoryModel(R.drawable.pack,"Pack"),
        CategoryModel(R.drawable.dot_icon,"Others")
    )
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var categoryRecyclerView:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView)

        implementCategoryAdapter()
        return  view
    }



    private fun implementCategoryAdapter() {
        categoryRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3) // 2 columns
            categoryAdapter = CategoryAdapter(requireContext())
            adapter = categoryAdapter
            categoryAdapter.setCategoryList(categoryItemsList)
        }
    }



}