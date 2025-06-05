package com.paul.groceryapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.AddressModel
import com.paul.groceryapp.Model.CategoryModel
import com.paul.groceryapp.R

class AddressAdapter(private val context: Context): RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    private var addressList: ArrayList<AddressModel> = ArrayList()
    private var selectedPosition: Int = -1

    fun setAddressList(newList: ArrayList<AddressModel>) {
        addressList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.AddressViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_address, parent, false)
        return AddressViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddressAdapter.AddressViewHolder, position: Int) {
        val currentItem = addressList[position]
        holder.categoryTextView.text = currentItem.title
        // Change background based on selection
        if (position == selectedPosition) {
            holder.itemView.findViewById<LinearLayout>(R.id.lineLayoutAddress)
                .setBackgroundResource(R.drawable.bg_selected) // Selected background
        } else {
            holder.itemView.findViewById<LinearLayout>(R.id.lineLayoutAddress)
                .setBackgroundResource(R.drawable.bg_unselected) // Default background
        }

        // Set click listener to update selected position
        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Refresh the adapter
        }

    }

    override fun getItemCount(): Int {
        return addressList.size
    }
 class AddressViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
     val categoryTextView : TextView = itemView.findViewById(R.id.tvTitle)

 }

}