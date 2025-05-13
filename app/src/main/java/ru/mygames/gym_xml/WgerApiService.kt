package ru.mygames.gym_xml

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WgerApiService {
    @GET("exercises")
    @Headers("x-rapidapi-key: 61d94d24e8msh9bb77c596e8979cp109ba8jsn78f109e685c7")
    suspend fun getExercises(
    ): List<ExerciseApi>

    @GET("exercises/name/{name}")
    @Headers("x-rapidapi-key: 61d94d24e8msh9bb77c596e8979cp109ba8jsn78f109e685c7")
    suspend fun getName(@Path("name") name: String): List<ExerciseApi>
}

