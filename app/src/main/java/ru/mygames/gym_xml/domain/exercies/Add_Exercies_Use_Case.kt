package ru.mygames.gym_xml.domain.exercies

class Add_Exercies_Use_Case(private val exerciesRepository: Exercies_Repository) {
    fun addExercies(exercies: Exercies) {
        exerciesRepository.addExercies(exercies)
    }
}