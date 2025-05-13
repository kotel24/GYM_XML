package ru.mygames.gym_xml

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [FavoriteExercise::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun projectDAO(): FavoriteDao

    companion object {
        @Volatile
        private var Instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "favorites.db"
                ).build().also { Instance = it }
            }
    }
}