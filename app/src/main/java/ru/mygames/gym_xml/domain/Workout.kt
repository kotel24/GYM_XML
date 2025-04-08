package ru.mygames.gym_xml.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Workout(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val equipment: List<String>,
    val muscles: List<String>,
    val images: List<ExerciseImage>,
    val duration: String = "20 min", // Будем вычислять
    val calories: String = "160 Kcal" // Заглушка
) : Parcelable

@Parcelize
data class ExerciseImage(
    val image: String
) : Parcelable
@Parcelize
data class ExerciseVideo(
    val video: String
) : Parcelable