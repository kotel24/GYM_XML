package ru.mygames.gym_xml.domain

data class Exercies(
    val id: Int,
    val name: String,
    val description: String,
    val durationInSeconds: String,
    val videoUrl: String,
)
