package dev.passerby.domain.usecases

import dev.passerby.domain.repos.MainRepository

class LoadPizzasUseCase(private val repository: MainRepository) {
    suspend operator fun invoke() = repository.loadPizzas()
}