package com.paul.groceryapp.repository

import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.dao.CartDao

class CartRepository(private val cartDao: CartDao) {
    val allItems = cartDao.getAllItems()

    suspend fun insert(item: CartItem) = cartDao.insertItem(item)
    suspend fun delete(item: CartItem) = cartDao.deleteItem(item)
    suspend fun deleteByItemId(itemId: Int) = cartDao.deleteItemById(itemId)
    suspend fun update(item: CartItem) = cartDao.updateItem(item)
    suspend fun clearCart() = cartDao.clearCart()
}