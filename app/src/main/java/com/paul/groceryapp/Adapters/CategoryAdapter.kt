package com.paul.groceryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.CategoryModel
import com.paul.groceryapp.Model.PopularPackModel




class CategoryAdapter(private val context:Context):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private var categoryList: ArrayList<CategoryModel> = ArrayList()
    private var selectedPosition: Int = -1

    fun setCategoryList(newList: ArrayList<CategoryModel>) {
        categoryList = newList
        notifyDataSetChanged()
    }

   

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_items, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categoryList[position]
        holder.categoryTextView.text = currentItem.categoryText
        holder.categoryImageView.setImageResource(currentItem.categoryImg)

        // Change background based on selection
        if (position == selectedPosition) {
            holder.itemView.findViewById<ImageView>(R.id.categoryImageView)
                .setBackgroundResource(R.drawable.shape_selected_background) // Selected background
        } else {
            holder.itemView.findViewById<ImageView>(R.id.categoryImageView)
                .setBackgroundResource(R.drawable.round_shape_green_bg) // Default background
        }

        // Set click listener to update selected position
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Refresh the adapter
        }


    }


    class CategoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val categoryTextView :TextView = itemView.findViewById(R.id.categoryTextView)
        val categoryImageView :ImageView = itemView.findViewById(R.id.categoryImageView)


    }
}