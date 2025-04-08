package ru.mygames.gym_xml.domain.home_exercies

import androidx.lifecycle.LiveData

interface Exercies_Repository{
    fun addExercies(exercies: Exercies)

    fun deleteExercies(exercies: Exercies)

    fun editExercies(exercies: Exercies)

    fun getExerciesById(id: Int): Exercies

    fun getExerciesList(): LiveData<List<Exercies>>
}