package dev.passerby.domain.models

data class PizzaModel(
    val category: Int,
    val id: Int,
    val imageUrl: String,
    val ingredients: String,
    val price: Int,
    val rating: Int,
    val sizes: List<Int>,
    val title: String
)