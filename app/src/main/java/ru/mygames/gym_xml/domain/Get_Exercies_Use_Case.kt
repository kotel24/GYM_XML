package ru.mygames.gym_xml.domain

import androidx.lifecycle.LiveData

class Get_Exercies_Use_Case (private val exerciesRepository: Exercies_Repository){
    fun getExercises(): LiveData<List<Exercies>>{
        return exerciesRepository.getExercies()
    }
}