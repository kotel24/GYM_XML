package ru.mygames.gym_xml


data class ExerciseApi(
    val id: Int,
    val uuid: String,
    val category: Category,
    val muscles: Array<ExercisуMuscles>?,
    val muscles_secondary: Array<ExercisуMuscles>?,
    val equipment: Array<Equipment>?,
    val images: Array <ExerciseImage>?,
    val videos: Array<ExerciseVideo>?
)
data class Category(
    val name: String
)
data class ExerciseImage(
    val image: String,
    val author_history:Array<String>
)
data class Equipment(
    val nameEquipment: String
)
data class ExerciseVideo(
    val video: String
)
data class ExercisуMuscles(
    val nameMuscles: String,
    val imageMuscles: String
)

data class ExerciseResponse(val results: List<ExerciseApi>)