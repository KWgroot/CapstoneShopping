package com.example.capstoneshopping.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capstoneshopping.Dao.GroceryDao
import com.example.capstoneshopping.Database.GroceryRoomDatabase
import com.example.capstoneshopping.Viewmodel.Grocery

class GroceryRepository(context: Context) {
    private val groceryDao: GroceryDao

    init {
        val database = GroceryRoomDatabase.getDatabase(context)
        groceryDao = database!!.groceryDao()
    }

    //Calls to perform actions set by the DAO to alter database information.

    fun getAllProducts(): LiveData<List<Grocery>>{
        return groceryDao.getAllProducts() ?: MutableLiveData(emptyList())
    }

    suspend fun insertGrocery(grocery: Grocery){
        groceryDao.insertGrocery(grocery)
    }

    suspend fun deleteGrocery(grocery: Grocery){
        groceryDao.deleteGrocery(grocery)
    }

    suspend fun deleteAll(){
        groceryDao.deleteAll()
    }

    suspend fun updateGrocery(grocery: Grocery){
        groceryDao.updateGrocery(grocery)
    }
}