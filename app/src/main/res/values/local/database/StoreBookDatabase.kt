package values.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import values.local.dao.StoreBookDao

abstract class StoreBookDatabase : RoomDatabase() {
    abstract fun storeBookDao(): values.local.dao.StoreBookDao

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