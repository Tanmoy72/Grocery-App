package com.paul.groceryapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.dataBase.CartDatabase
import com.paul.groceryapp.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CartRepository
    val allCartItems get() = repository.allItems

    init {
        val dao = CartDatabase.getDatabase(application).cartDao()
        repository = CartRepository(dao)
    }

    fun insert(item: CartItem) = viewModelScope.launch { repository.insert(item) }
    fun update(item: CartItem) = viewModelScope.launch { repository.update(item) }
    fun delete(item: CartItem) = viewModelScope.launch { repository.delete(item) }
    fun deleteByItemId(itemId: Int) = viewModelScope.launch { repository.deleteByItemId(itemId) }
    fun clear() = viewModelScope.launch { repository.clearCart() }
}