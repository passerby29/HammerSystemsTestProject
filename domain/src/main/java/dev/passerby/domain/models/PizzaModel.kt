package dev.passerby.domain.models

data class PizzaModel(
    val category: Int,
    val id: Int,
    val imageUrl: String,
    val price: Int,
    val rating: Int,
    val sizes: List<Int>,
    val title: String,
    val types: List<Int>
)