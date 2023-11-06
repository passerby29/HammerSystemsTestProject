package dev.passerby.domain.usecases

import dev.passerby.domain.repos.MainRepository

class GetPizzaListUseCase(private val repository: MainRepository) {

    operator fun invoke() = repository.getPizzaList()
}