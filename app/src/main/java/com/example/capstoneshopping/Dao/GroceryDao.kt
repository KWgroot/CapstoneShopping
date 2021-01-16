package com.example.capstoneshopping.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneshopping.Viewmodel.Grocery

@Dao
interface GroceryDao {

    //When the Data Access Object(s) are called perform simple
    //database operations to alter/update information.

    @Query("SELECT * FROM grocery_table")
    fun getAllProducts(): LiveData<List<Grocery>>

    @Insert
    suspend fun insertGrocery(grocery: Grocery)

    @Delete
    suspend fun deleteGrocery(grocery: Grocery)

    @Query("DELETE FROM grocery_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateGrocery(grocery: Grocery)
}