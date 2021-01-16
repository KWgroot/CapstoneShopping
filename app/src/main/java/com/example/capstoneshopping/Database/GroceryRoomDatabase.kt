package com.example.capstoneshopping.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstoneshopping.Converters.Converters
import com.example.capstoneshopping.Dao.GroceryDao
import com.example.capstoneshopping.Viewmodel.Grocery

@Database(entities = [Grocery::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GroceryRoomDatabase: RoomDatabase() {
    abstract fun groceryDao(): GroceryDao

    companion object{
        private const val DATABASE_NAME = "GROCERY_DB"

        //Generate the ROOM database and return the instance when asked for.

        @Volatile
        private var groceryRoomDatabaseInstance: GroceryRoomDatabase? = null

        fun getDatabase(context: Context): GroceryRoomDatabase?{
            if (groceryRoomDatabaseInstance == null){
                synchronized(GroceryRoomDatabase::class.java){
                    if (groceryRoomDatabaseInstance == null){
                        groceryRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            GroceryRoomDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return groceryRoomDatabaseInstance
        }
    }
}