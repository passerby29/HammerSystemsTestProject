package dev.passerby.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.passerby.data.converters.JsonConverters
import dev.passerby.data.models.db.PizzaDbModel

@Database(
    entities = [PizzaDbModel::class],
    exportSchema = false,
    version = 2
)
@TypeConverters(JsonConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pizzaDao(): PizzaDao

    companion object {

        private var db: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "main.db"

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

}