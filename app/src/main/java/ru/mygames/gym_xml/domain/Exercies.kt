package ru.mygames.gym_xml.domain

data class Exercies(
    val name: String,
    val description: String,
    val durationOrCount: String,
    val videoUrl: String,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID,
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
