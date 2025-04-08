package ru.mygames.gym_xml

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WgerApiService {
    @GET("exercise")
    @Headers("Authorization: 70e53ff474e9a480bb3c61047ab2c83c9781b415")
    suspend fun getExercises(
        //@Query("equipment") equipment: Equipment,
//        @Query("status") status: Int = 2,
    ): ExerciseResponse

    @GET("equipment/{id}/")
    @Headers("Authorization: 70e53ff474e9a480bb3c61047ab2c83c9781b415")
    suspend fun getEquipmentById(@Path("id") id: Int): Equipment
}

