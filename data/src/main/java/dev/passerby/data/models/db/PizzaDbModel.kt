package dev.passerby.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("pizzas")
data class PizzaDbModel(
    val category: Int,
    @PrimaryKey
    val id: Int,
    val imageUrl: String,
    val ingredients: String,
    val price: Int,
    val rating: Int,
    val sizes: List<Int>,
    val title: String
)