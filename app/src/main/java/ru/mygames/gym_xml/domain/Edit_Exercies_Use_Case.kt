package ru.mygames.gym_xml.domain

class Edit_Exercies_Use_Case (private val exerciesRepository: Exercies_Repository){
    fun editExercies(exercies: Exercies){
        exerciesRepository.editExercies(exercies)
    }
}