package com.example.capstoneshopping.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneshopping.R
import com.example.capstoneshopping.Viewmodel.Grocery
import kotlinx.android.synthetic.main.item.view.*

class GroceryAdapter(private val groceries: List<Grocery>): RecyclerView.Adapter<GroceryAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun databind(grocery: Grocery){
            itemView.tvProduct.text = grocery.product
            itemView.tvDate.text = grocery.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return groceries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(groceries[position])
    }
}