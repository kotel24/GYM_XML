package ru.mygames.gym_xml.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mygames.gym_xml.domain.workout.Workout
import ru.mygames.gym_xml.domain.workout.WorkoutRepository

class WorkoutViewModel : ViewModel() {
    val repository = WorkoutRepository()
    val workouts = MutableLiveData<List<Workout>>()
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String?>()

    fun loadWorkouts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = repository.getExercises()

                workouts.postValue(result)
                error.postValue(null)
            } catch (e: Exception) {
                error.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
    fun loadWorkoutsName(name:String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = repository.getExercisesWithName(name)

                workouts.postValue(result)
                error.postValue(null)
            } catch (e: Exception) {
                error.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}