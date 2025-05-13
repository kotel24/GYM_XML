package ru.mygames.gym_xml


data class ExerciseApi(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: Int,
    val name: String,
    val target: String,
    val secondaryMuscles: Array<String>,
    val instructions: Array<String>
)