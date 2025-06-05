package com.paul.groceryapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.paul.groceryapp.Model.SaveItem
import com.paul.groceryapp.dataBase.SaveDatabase
import com.paul.groceryapp.repository.SaveRepository
import kotlinx.coroutines.launch

class SaveViewModel(application: Application) : AndroidViewModel(application)  {
    private val repository: SaveRepository
    val allSaveItems get() = repository.allSaveItems
    init {
        val dao = SaveDatabase.getSaveDataBase(application).saveDao()
        repository = SaveRepository(dao)
    }
    fun insert(item: SaveItem) = viewModelScope.launch { repository.insert(item) }
    fun update(item: SaveItem) = viewModelScope.launch { repository.update(item) }
    fun delete(item: SaveItem) = viewModelScope.launch { repository.delete(item) }
    fun deleteByItemId(itemId: Int) = viewModelScope.launch { repository.deleteByItemId(itemId) }

}