package com.example.capstoneshopping.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.capstoneshopping.Viewmodel.Grocery

@Dao
interface GroceryDao {

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): LiveData<List<Grocery>>

    @Insert
    suspend fun insertGrocery(grocery: Grocery)

    @Delete
    suspend fun deleteGrocery(grocery: Grocery)

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}