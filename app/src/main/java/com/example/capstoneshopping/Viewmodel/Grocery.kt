package com.example.capstoneshopping.Viewmodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "product_table")
data class Grocery(

    @ColumnInfo(name = "product")
    var product: String,

    @ColumnInfo(name = "date")
    var date: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
)