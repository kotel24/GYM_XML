package ru.mygames.gym_xml.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mygames.gym_xml.data.Exercies_Repository_Impl
import ru.mygames.gym_xml.domain.Delete_Exercies_Use_Case
import ru.mygames.gym_xml.domain.Edit_Exercies_Use_Case
import ru.mygames.gym_xml.domain.Exercies
import ru.mygames.gym_xml.domain.Get_Exercies_Use_Case

open class MainViewModel:ViewModel() {
    private val repository = Exercies_Repository_Impl
    private val getExercisesUseCase = Get_Exercies_Use_Case(repository)
    private val deleteExercises = Delete_Exercies_Use_Case(repository)
    private val editExercises = Edit_Exercies_Use_Case(repository)

    val exercieslist = getExercisesUseCase.getExercises()

    fun deleteExercises(exercies: Exercies) {
        deleteExercises.deleteExercies(exercies)
    }

    fun editExercises(exercies: Exercies) {
        val newItem = exercies.copy(enabled = !exercies.enabled)
        editExercises.editExercies(newItem)
    }
}