package com.example.capstoneshopping.UI

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.capstoneshopping.R
import com.example.capstoneshopping.Viewmodel.GroceryViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val viewModel: GroceryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
        addFab.setOnClickListener {
            navController.navigate(
                R.id.action_groceryListFragment_to_addGroceryFragment
            )
        }

        btnScan.setOnClickListener {
            scanBarcode()
        }

        fabToggler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bin -> {
                viewModel.deleteAllGroceries()
                Toast.makeText(applicationContext, R.string.deleted_groceries, Toast.LENGTH_SHORT).show()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fabToggler(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.addGroceryFragment)){
                addFab.hide()
            }else{
                addFab.show()
            }
        }
    }

    private fun scanBarcode(){
        val scanner = IntentIntegrator(this)

        scanner.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            val result =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}