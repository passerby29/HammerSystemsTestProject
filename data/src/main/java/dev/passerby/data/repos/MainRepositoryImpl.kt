package dev.passerby.data.repos

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import dev.passerby.data.database.AppDatabase
import dev.passerby.data.mappers.PizzaMapper
import dev.passerby.data.models.dto.PizzaDto
import dev.passerby.data.network.ApiFactory
import dev.passerby.data.network.BaseResponse
import dev.passerby.domain.models.PizzaModel
import dev.passerby.domain.repos.MainRepository

class MainRepositoryImpl(application: Application) : MainRepository {

    private val db = AppDatabase.getInstance(application)
    private val apiService = ApiFactory.apiService
    private val pizzaDao = db.pizzaDao()
    private val pizzaMapper = PizzaMapper()
    private var pizzaListResult: MutableLiveData<BaseResponse<PizzaDto>> = MutableLiveData()


    override fun getPizzaList(): LiveData<List<PizzaModel>> {
        val dbModelList = pizzaDao.getPizzaList()
        val pizzaList = dbModelList.map {
            it.map { dbModel ->
                pizzaMapper.mapDbModelToEntity(dbModel)
            }
        }
        return pizzaList
    }

    override suspend fun loadPizzas() {
        pizzaListResult.value = BaseResponse.Loading()
        try {
            val response = apiService.loadPizzas()
            if (response.code() == 200) {
                pizzaListResult.value = BaseResponse.Success(response.body())
                val dbModelList = response.body()?.map {
                    pizzaMapper.mapDtoToDbModel(it)
                }
                pizzaDao.insertCoin(dbModelList ?: emptyList())
                Log.d(TAG, "loadPizzasTry: ${response.body()?.get(0)}")
            } else {
                pizzaListResult.value = BaseResponse.Error(response.message())
                Log.d(TAG, "loadPizzasElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadPizzasCatch: $ex")
            pizzaListResult.value = BaseResponse.Error(ex.message)
        }
    }

    companion object {
        private const val TAG = "MainRepositoryImplTag"
    }
}