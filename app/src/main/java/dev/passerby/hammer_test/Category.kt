package dev.passerby.hammer_test

import dev.passerby.domain.models.PizzaModel

data class Category(val name: String, val items: List<PizzaModel>, val pos: Int)