package com.example.capstoneshopping.UI

import android.app.Activity
import android.content.Intent
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

        btnScan.setOnClickListener {
            scanBarcode()
        }
    }

    private fun onAddGrocery(){
        val groceryName = tiProduct.text.toString() //Get the manually input name for the product

        val currentDate = LocalDate.now() //Get the current day of the month because a year is not needed for a fridge storage.
        val result: ZonedDateTime = currentDate.atStartOfDay(ZoneId.systemDefault())

        if (groceryName.isNotBlank()){
            viewModel.insertGrocery(Grocery(
                groceryName, Date.from(result.toInstant())
            ))
        } else {
            Toast.makeText(activity, R.string.no_product, Toast.LENGTH_SHORT).show()
        }
    }

    private fun scanBarcode(){
        val scanner = IntentIntegrator(activity)

        scanner.initiateScan() //Starts a google API to open the camera and look for barcodes and QR codes.
    }
}