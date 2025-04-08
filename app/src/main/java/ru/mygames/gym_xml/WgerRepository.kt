package ru.mygames.gym_xml

import android.util.Log
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WgerRepository {
    class ExerciseRepository {
        private val api = Retrofit.Builder()
            .baseUrl("https://wger.de/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WgerApiService::class.java)

        suspend fun getExerciseInfo(): ExerciseResponse {
            return api.getExercises()
        }

        suspend fun getEquipmentById(equipmentId: Int): Equipment {
            return api.getEquipmentById(equipmentId)
        }

        suspend fun getExercisesWithEquipment(): List<ExerciseApi> = coroutineScope {
            val exerciseResponse = getExerciseInfo() // Получаем данные об упражнениях

            exerciseResponse.results.map { exercise ->
                // Для каждого упражнения запускаем параллельно запросы для каждого id оборудования
                val equipmentDeferred = exercise.equipment.map { equipmentId ->
                    async { getEquipmentById(equipmentId).name }
                }
                // Ожидаем завершения всех запросов и собираем результат
                val equipmentList = equipmentDeferred.awaitAll()

                Log.i("RESPONSE2", equipmentList.toString())

                ExerciseApi(
                    id = exercise.id,
                    uuid = exercise.uuid,
                    category = exercise.category,
                    created = exercise.created,
                    last_update = exercise.last_update,
                    muscles = exercise.muscles,
                    muscles_secondary = exercise.muscles_secondary,
                    equipment = exercise.equipment,
                    variations = exercise.variations
                )
            }
        }
    }
}