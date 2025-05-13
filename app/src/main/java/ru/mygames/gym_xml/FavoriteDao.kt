package ru.mygames.gym_xml

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteExercise)
    @Delete
    suspend fun removeFavorite(favorite: FavoriteExercise)
    @Query("SELECT * FROM favorites WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): FavoriteExercise?
    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<FavoriteExercise>>
}