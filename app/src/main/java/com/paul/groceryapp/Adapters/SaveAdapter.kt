package com.paul.groceryapp.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.Model.SaveItem




import com.paul.groceryapp.R

class SaveAdapter(private val saveItems: List<SaveItem>,private val listener: OnSaveItemClickListener): RecyclerView.Adapter<SaveAdapter.SaveViewHolder>() {

interface OnSaveItemClickListener{
    fun onSaveItemDelete(item: SaveItem, position: Int)




}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_save, parent, false)
        return SaveViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return saveItems.size


    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        val currentItem = saveItems[position]
        holder.saveName.text = currentItem.saveName
        holder.saveImg.setImageResource(currentItem.saveImg)
        holder.savePrice.text = currentItem.savePrice.toString()
        holder.saveDeleteItems.setOnClickListener {
           listener.onSaveItemDelete(currentItem,position)
        }

    }

    class SaveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val saveName : TextView = itemView.findViewById(R.id.saveText)
        val saveImg : ImageView = itemView.findViewById(R.id.saveImg)
        val savePrice : TextView = itemView.findViewById(R.id.savePrice)
        val saveDeleteItems : ImageView = itemView.findViewById(R.id.saveDeleteItems)


    }

}