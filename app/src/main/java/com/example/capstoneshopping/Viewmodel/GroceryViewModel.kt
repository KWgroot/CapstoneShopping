package com.example.capstoneshopping.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstoneshopping.Repository.GroceryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val groceryRepository = GroceryRepository(application.applicationContext)

    val groceries: LiveData<List<Grocery>> = groceryRepository.getAllProducts()

    fun insertGrocery(grocery: Grocery){
        ioScope.launch { groceryRepository.insertGrocery(grocery) }
    }

    fun deleteGrocery(grocery: Grocery){
        ioScope.launch { groceryRepository.deleteGrocery(grocery) }
    }

    fun deleteAllGroceries(){
        ioScope.launch { groceryRepository.deleteAll() }
    }

    fun updateGroceries(grocery: Grocery){
        ioScope.launch { groceryRepository.updateGrocery(grocery) }
    }
}