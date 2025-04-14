package ru.mygames.gym_xml

import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mygames.gym_xml.domain.Workout

class WgerRepository {
    class ExerciseRepository {
        private val api = Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WgerApiService::class.java)


        suspend fun getExercisesWithEquipment(): List<Workout> = coroutineScope {
            val exerciseResponse = api.getExercises()
            exerciseResponse.results.map { exercise -> exercise.toWorkout()
//                val imageDeferred = exercise.images.map { image -> image.image }
//                val videoDeferred = exercise.videos
//                Log.i("RESPONSE2", imageDeferred.toString())
//                Log.i("RESPONSE3", videoDeferred.toString())
//                ExerciseApi(
//                id = exercise.id,
//                uuid = exercise.uuid,
//                muscles = exercise.muscles,
//                muscles_secondary = exercise.muscles_secondary,
//                equipment = exercise.equipment,
//                images = exercise.images,
//                videos = exercise.videos
//                )
            }
        }
        private fun ExerciseApi.toWorkout() = Workout(
            id = id,
            category = category.name,
            equipment = equipment.map { it.nameEquipment}.toString(),
            muscles = muscles.map { it.nameMuscles },
            muscles_secondary = muscles_secondary.map { it.nameMuscles },
            images = images.map { it.image },
            videos = videos.map { it.video }
        )
    }
}