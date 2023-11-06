package dev.passerby.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.passerby.data.models.db.PizzaDbModel

interface PizzaDao {

    @Query("select * from pizzas")
    fun getPizzaList(): LiveData<List<PizzaDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coinsList: List<PizzaDbModel>)
}