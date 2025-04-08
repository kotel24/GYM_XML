package ru.mygames.gym_xml


data class ExerciseApi(
    val id: Int,
    val uuid: String,
    val created: String,
    val last_update: String,
    val category: Int,
    val muscles: Array<Int>,
    val muscles_secondary: Array<Int>,
    val equipment: Array<Int>,
    val variations: Int
)

data class Category(
    val id: Int,
    val name: String
)
data class Equipment(
    val id: Int,
    val name: String
)
data class Video(
    val exercise: Int,
    val video: String
)

data class ExerciseResponse(val results: List<ExerciseApi>)