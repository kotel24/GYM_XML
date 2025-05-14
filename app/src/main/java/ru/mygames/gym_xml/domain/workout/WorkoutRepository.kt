package ru.mygames.gym_xml.domain.workout

import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WorkoutRepository {
    private val api = Retrofit.Builder()
        .baseUrl("https://exercisedb.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WgerApiService::class.java)


    suspend fun getExercises(): List<Workout> = coroutineScope {
        val exerciseResponse = api.getExercises()
        exerciseResponse.map { exercise ->
            exercise.toWorkout()
        }
    }
    suspend fun getExercisesWithName(name:String): List<Workout> = coroutineScope {
        val exerciseNameResponse = api.getName(name)
        exerciseNameResponse.map { exercise ->
            exercise.toWorkout()
        }
    }

    private fun ExerciseApi.toWorkout() = Workout(
        bodyPart =  bodyPart,
        equipment =  equipment,
        gifUrl = gifUrl,
        id = id,
        name =  name,
        target = target,
        secondaryMuscles = secondaryMuscles.firstOrNull() ?: "",
        instructions = instructions.joinToString("\n")
    )
}
