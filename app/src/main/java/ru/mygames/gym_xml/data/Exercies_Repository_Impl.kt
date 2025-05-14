package ru.mygames.gym_xml.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mygames.gym_xml.domain.exercies.Exercies
import ru.mygames.gym_xml.domain.exercies.Exercies_Repository
import kotlin.random.Random

object Exercies_Repository_Impl: Exercies_Repository {

    private var exerciesList = sortedSetOf<Exercies>({ o1, o2 -> o1.id.compareTo(o2.id)})

    private var mutableListLiveData = MutableLiveData<List<Exercies>>()

    init {
        for(i in 0 until 10){
            val exerciesItem = Exercies("Name $i", enabled = Random.nextBoolean(), durationOrCount = i.toString())
            addExercies(exerciesItem)
        }
    }

    private var autoIncrementId = 0
    override fun addExercies(exercies: Exercies) {
        if (exercies.id == Exercies.UNDEFINED_ID)
            exercies.id = autoIncrementId++
        exerciesList.add(exercies)
        updateExercies()
    }

    override fun deleteExercies(exercies: Exercies) {
        exerciesList.remove(exercies)
        updateExercies()
    }

    override fun editExercies(exercies: Exercies) {
        deleteExercies(getExerciesById(exercies.id))
        addExercies(exercies)
    }

    override fun getExerciesById(id: Int): Exercies {
        return exerciesList.find { it.id == id }?: throw RuntimeException("Element with id $id not found")
    }

    override fun getExerciesList(): LiveData<List<Exercies>> {
        return mutableListLiveData
    }

    private fun updateExercies(){
        mutableListLiveData.postValue(exerciesList.toList())
    }
}