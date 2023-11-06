package dev.passerby.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.domain.models.PizzaModel

interface MainRepository {

    fun getPizzaList(): LiveData<PizzaModel>

    suspend fun loadPizzas()
}