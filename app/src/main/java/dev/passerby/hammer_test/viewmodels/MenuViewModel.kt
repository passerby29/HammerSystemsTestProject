package dev.passerby.hammer_test.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.passerby.data.repos.MainRepositoryImpl
import dev.passerby.domain.usecases.GetPizzaListUseCase
import dev.passerby.domain.usecases.LoadPizzasUseCase
import kotlinx.coroutines.launch

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl(application)
    private val getPizzaListUseCase = GetPizzaListUseCase(repository)
    private val loadPizzasUseCase = LoadPizzasUseCase(repository)

    val pizzaList = getPizzaListUseCase()

    init {
        viewModelScope.launch {
            loadPizzasUseCase()
        }
    }
}