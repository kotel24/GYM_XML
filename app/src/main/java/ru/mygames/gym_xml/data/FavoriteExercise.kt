package ru.mygames.gym_xml.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteExercise(
    @PrimaryKey val id: Int,
    val equipment: String,
    val bodyPart: String,
    val gifUrl: String,
    val name: String,
    val target: String,
    val secondaryMuscles: String,
    val instructions: String
)