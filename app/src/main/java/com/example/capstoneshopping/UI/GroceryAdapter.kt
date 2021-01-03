package com.example.capstoneshopping.UI

import android.annotation.SuppressLint
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
import java.time.ZoneId

class GroceryAdapter(private val groceries: List<Grocery>): RecyclerView.Adapter<GroceryAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun databind(grocery: Grocery){
            itemView.tvProduct.text = grocery.product
            val date = grocery.date
            val localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            val day = localDate.dayOfMonth
            val month = localDate.month //Convert the date into a nice string that shows the day and month ONLY

            itemView.tvDate.text = "Placed in fridge on: $day $month"
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