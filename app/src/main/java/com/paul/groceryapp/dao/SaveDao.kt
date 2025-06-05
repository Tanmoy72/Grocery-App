package com.paul.groceryapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.paul.groceryapp.Model.CartItem
import com.paul.groceryapp.Model.SaveItem

@Dao
interface SaveDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SaveItem)

    @Update
    suspend fun updateItem(item: SaveItem)

    @Query("DELETE FROM save_items WHERE id = :itemId")
    suspend fun deleteItemById(itemId: Int)

    @Delete
    suspend fun deleteItem(item: SaveItem)

    @Query("SELECT * FROM save_items")
    fun getAllSaveItems(): LiveData<List<SaveItem>>
}