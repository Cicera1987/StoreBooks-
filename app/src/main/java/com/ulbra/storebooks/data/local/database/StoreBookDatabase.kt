package com.ulbra.storebooks.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ulbra.storebooks.data.local.dao.StoreBookDao
import com.ulbra.storebooks.data.local.entities.StoreBookEntity

@Database(entities = [StoreBookEntity ::class], version = 1, exportSchema = false)
abstract class StoreBookDatabase : RoomDatabase() {
    abstract fun bookDao(): StoreBookDao

    companion object {
        @Volatile
        private var INSTANCE: StoreBookDatabase? = null

        fun getDatabase(context: Context): StoreBookDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreBookDatabase::class.java,
                    "storebook_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}