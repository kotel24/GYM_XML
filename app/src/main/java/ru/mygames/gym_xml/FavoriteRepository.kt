package ru.mygames.gym_xml

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    fun getAllFavorites(): Flow<List<FavoriteExercise?>> {
        return favoriteDao.getAll()
    }

    suspend fun addFavorite(favorite: FavoriteExercise) {
        favoriteDao.insert(favorite)
    }

    suspend fun removeFavorite(favorite: FavoriteExercise) {
        favoriteDao.removeFavorite(favorite)
    }

    suspend fun isFavorite(id: Int): Boolean {
        return favoriteDao.getById(id) != null
    }
    fun FavoriteExercise.toWorkout() = Workout(
        bodyPart =  bodyPart,
        equipment =  equipment,
        gifUrl = gifUrl,
        id = id,
        name =  name,
        target = target,
        secondaryMuscles = secondaryMuscles,
        instructions = instructions
    )
}