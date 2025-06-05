package com.paul.groceryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.PopularPackModel




class PopularPackAdapter(private val context:Context, private val listener:OnItemClickListener):RecyclerView.Adapter<PopularPackAdapter.PopularPackViewHolder>() {


    private var popularList: ArrayList<PopularPackModel> = ArrayList()

    fun setPopularList(newList: ArrayList<PopularPackModel>) {
        popularList = newList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: PopularPackModel)
        fun onAddToCartClick(item: PopularPackModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularPackViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.popular_pack_items, parent, false)
        return PopularPackViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    override fun onBindViewHolder(holder: PopularPackViewHolder, position: Int) {
        val currentItem = popularList[position]
        holder.itemName.text = currentItem.name
        holder.vegetableImg.setImageResource(currentItem.img)
        holder.item2.text = "Rs. ${currentItem.price}"

        holder.itemView.setOnClickListener {
            listener.onItemClick(currentItem)
        }
        holder.addToCartImageView.setOnClickListener {
            listener.onAddToCartClick(currentItem)
        }
    }


    class PopularPackViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val itemName :TextView = itemView.findViewById(R.id.item1)
        val vegetableImg :ImageView = itemView.findViewById(R.id.vegetableImg)
        val item2 :TextView = itemView.findViewById(R.id.item2)
        val addToCartImageView= itemView.findViewById<ImageView>(R.id.addToCartImageView)



    }
}