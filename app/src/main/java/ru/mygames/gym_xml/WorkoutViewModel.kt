package ru.mygames.gym_xml

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mygames.gym_xml.domain.Workout

class WorkoutViewModel : ViewModel() {
    private val repository = WgerRepository.ExerciseRepository()
    val workouts = MutableLiveData<List<Workout>>()
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<String?>()

    fun loadWorkouts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val result = repository.getExercisesWithEquipment()

                Log.i("RESPONSE1", result.toString())

                //workouts.postValue(result)
                error.postValue(null)
            } catch (e: Exception) {
                error.postValue(e.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}