package com.paul.groceryapp.repository

import com.paul.groceryapp.Model.SaveItem
import com.paul.groceryapp.dao.SaveDao

class SaveRepository(private val saveDao: SaveDao)  {
    val allSaveItems = saveDao.getAllSaveItems()
    suspend fun insert(item: SaveItem) = saveDao.insertItem(item)
    suspend fun delete(item: SaveItem) = saveDao.deleteItem(item)
    suspend fun deleteByItemId(itemId: Int) = saveDao.deleteItemById(itemId)
    suspend fun update(item: SaveItem) = saveDao.updateItem(item)


}