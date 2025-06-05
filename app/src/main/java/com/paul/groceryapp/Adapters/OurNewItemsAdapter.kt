package com.paul.groceryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.PopularPackModel
import com.paul.groceryapp.PopularPackAdapter.OnItemClickListener


class OurNewItemsAdapter(private val context:Context,private val listener: OnItemClickListener):RecyclerView.Adapter<OurNewItemsAdapter.OurNewItemsViewHolder>() {


    private var newItemsList: ArrayList<PopularPackModel> = ArrayList()

    fun setNewList(newList: ArrayList<PopularPackModel>) {
        newItemsList = newList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: PopularPackModel)
        fun onAddToCartClick(item: PopularPackModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurNewItemsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.our_new_items, parent, false)
        return OurNewItemsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newItemsList.size
    }

    override fun onBindViewHolder(holder: OurNewItemsViewHolder, position: Int) {
        val currentItem = newItemsList[position]
        holder.foodNameTextView.text = currentItem.name
        holder.newItemsImg.setImageResource(currentItem.img)
        holder.item2.text =  "Rs. ${currentItem.price}"
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
        holder.addToCart.setOnClickListener {
            listener.onAddToCartClick(currentItem)

        }

    }


    class OurNewItemsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val foodNameTextView :TextView = itemView.findViewById(R.id.foodNameTextView)
        val newItemsImg :ImageView = itemView.findViewById(R.id.newItemsImg)
        val item2 :TextView = itemView.findViewById(R.id.itemPrice)
        val addToCart= itemView.findViewById<ImageView>(R.id.addToCart)


    }
}