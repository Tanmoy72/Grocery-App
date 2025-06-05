package com.paul.groceryapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.paul.groceryapp.CategoryAdapter.CategoryViewHolder

import com.paul.groceryapp.Model.PaymentModel
import com.paul.groceryapp.R

class PaymentAdapter(context: Context):RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder>() {
    private var selectedPosition: Int = -1
    private var pamymentList: ArrayList<PaymentModel> = ArrayList()
    fun setPaymentList(newList: ArrayList<PaymentModel>) {
        pamymentList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_payment, parent, false)
        return PaymentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pamymentList.size
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val currentItem = pamymentList[position]
        holder.imgName.text = currentItem.name
        holder.img.setImageResource(currentItem.img)

        // Change background based on selection
        if (position == selectedPosition) {
            holder.itemView.findViewById<CardView>(R.id.paymentCardView)
                .setBackgroundResource(R.drawable.bg_selected) // Selected background
        } else {
            holder.itemView.findViewById<CardView>(R.id.paymentCardView)
                .setBackgroundResource(R.drawable.uncelect_bk) // Default background
        }

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged() // Refresh the adapter
        }
    }

    class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgName: TextView = itemView.findViewById(R.id.cardName)
        val img: ImageView = itemView.findViewById(R.id.cardImg)

    }

}