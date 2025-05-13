package ru.mygames.gym_xml

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Workout(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: Int,
    val name: String,
    val target: String,
    val secondaryMuscles: String,
    val instructions: String
) : Parcelable
