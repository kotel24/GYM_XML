package ru.mygames.gym_xml.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.mygames.gym_xml.ExercisуMuscles


@Parcelize
data class Workout(
    val id: Int,
    val category: String,
    val equipment: String,
    val muscles: List<String>,
    val muscles_secondary: List<String>,
    val images: List<String>,
    val videos:List<String>
) : Parcelable


@Parcelize
data class ExerciseVideo(
    val video: String
) : Parcelable