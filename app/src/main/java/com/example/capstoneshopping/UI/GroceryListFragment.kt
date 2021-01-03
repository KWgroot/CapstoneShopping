package com.example.capstoneshopping.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneshopping.R
import com.example.capstoneshopping.Viewmodel.Grocery
import com.example.capstoneshopping.Viewmodel.GroceryViewModel
import kotlinx.android.synthetic.main.fragment_fridgelist.*

class GroceryListFragment : Fragment(){
    private lateinit var groceryAdapter: GroceryAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var groceries: ArrayList<Grocery> = arrayListOf()
    private val viewModel: GroceryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridgelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        observeAddGrocery()
    }

    private fun observeAddGrocery(){
        viewModel.groceries.observe(viewLifecycleOwner, Observer { groceries ->
            this.groceries.clear()
            this.groceries.addAll(groceries)
            this.groceries.sortBy { it.date }

            groceryAdapter.notifyDataSetChanged()
        })
    }

    private fun initRv(){
        groceryAdapter = GroceryAdapter(groceries)
        viewManager = LinearLayoutManager(activity)

        createItemTouchHelper().attachToRecyclerView(rvGroceryList)

        rvGroceryList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = groceryAdapter
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper{
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val groceryToDelete = groceries[position]
                viewModel.deleteGrocery(groceryToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}