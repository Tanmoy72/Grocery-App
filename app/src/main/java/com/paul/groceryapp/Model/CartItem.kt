package com.paul.groceryapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val imageResId: Int,
    var quantity: Int,
    var pricePerItem: Double
) {
    fun getFormatedTotalPrice(): String {
        val totalPrice: Double = quantity * pricePerItem
        return "Rs.$totalPrice"
    }
}
