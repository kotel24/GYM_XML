package ru.mygames.gym_xml

import retrofit2.http.GET
import retrofit2.http.Headers

interface WgerApiService {
    @GET("exerciseinfo")
    @Headers("Authorization: 70e53ff474e9a480bb3c61047ab2c83c9781b415")
    suspend fun getExercises(
    ): ExerciseResponse
}

