package ru.mygames.gym_xml.domain.exercies

class Delete_Exercies_Use_Case (private val exerciesRepository: Exercies_Repository){
    fun deleteExercies(exercies: Exercies) {
        exerciesRepository.deleteExercies(exercies)
    }
}