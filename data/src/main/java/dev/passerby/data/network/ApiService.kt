package dev.passerby.data.network

import dev.passerby.data.models.dto.PizzaDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("pizza-list")
    suspend fun loadPizzas(): Response<PizzaDto>

}