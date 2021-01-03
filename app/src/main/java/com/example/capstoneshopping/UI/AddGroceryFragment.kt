package com.example.capstoneshopping.UI

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstoneshopping.R
import com.example.capstoneshopping.Viewmodel.Grocery
import com.example.capstoneshopping.Viewmodel.GroceryViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_item_add.*
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddGroceryFragment : Fragment() {
    private val viewModel: GroceryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        saveFab.setOnClickListener{
            onAddGrocery()
            findNavController().popBackStack()
        }
    }

    private fun onAddGrocery(){
        val groceryName = tiProduct.text.toString()

        /*val formatter = DateTimeFormatter.ofPattern("dd MM yyyy")
        val currentDate = LocalDate.now().toString()
        val date = LocalDate.parse(currentDate, formatter)
        val result: ZonedDateTime = date.atStartOfDay(ZoneId.systemDefault())*/

        val currentDate = LocalDate.now()
        val result: ZonedDateTime = currentDate.atStartOfDay(ZoneId.systemDefault())

        if (groceryName.isNotBlank()){
            viewModel.insertGrocery(Grocery(
                groceryName, Date.from(result.toInstant())
            ))
        } else {
            Toast.makeText(activity, R.string.no_product, Toast.LENGTH_SHORT).show()
        }
    }
}