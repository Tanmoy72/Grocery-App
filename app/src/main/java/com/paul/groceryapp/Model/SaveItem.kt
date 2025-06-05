package com.paul.groceryapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "save_items")
data class SaveItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val saveName:String,
    val saveImg:Int,
    val savePrice: Double
)
