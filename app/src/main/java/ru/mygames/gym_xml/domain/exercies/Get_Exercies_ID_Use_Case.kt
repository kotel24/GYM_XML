package ru.mygames.gym_xml.domain.exercies

class Get_Exercies_ID_Use_Case(private val exerciesRepository: Exercies_Repository) {
    fun getExerciesById(exerciesById: Int): Exercies {
        return exerciesRepository.getExerciesById(exerciesById)
    }
}