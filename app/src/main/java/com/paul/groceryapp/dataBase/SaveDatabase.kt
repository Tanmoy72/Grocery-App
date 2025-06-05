package com.paul.groceryapp.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.paul.groceryapp.Model.SaveItem
import com.paul.groceryapp.dao.SaveDao

@Database(entities = [SaveItem::class], version = 1)
abstract class SaveDatabase: RoomDatabase() {
    abstract fun saveDao(): SaveDao
    companion object{
        @Volatile
        private var INSTANCE: SaveDatabase? = null
         fun getSaveDataBase(context: Context): SaveDatabase {
             return INSTANCE ?: synchronized(this){
                 Room.databaseBuilder(
                     context.applicationContext,
                     SaveDatabase::class.java,
                     "save_database"
                 ).build().also {
                     INSTANCE = it
                 }
             }
         }


    }

}