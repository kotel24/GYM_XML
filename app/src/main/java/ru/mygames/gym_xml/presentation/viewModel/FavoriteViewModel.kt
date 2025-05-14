package ru.mygames.gym_xml.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.mygames.gym_xml.data.FavoriteExercise
import ru.mygames.gym_xml.data.FavoriteRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    // Получаем список избранных как StateFlow
    val favorites: StateFlow<List<FavoriteExercise?>> = repository.getAllFavorites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Добавление в избранное
    fun addFavorite(favorite: FavoriteExercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(favorite)
        }
    }

    // Удаление из избранного
    fun removeFavorite(favorite: FavoriteExercise) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavorite(favorite)
        }
    }

    // Проверка: есть ли элемент с таким id в списке избранного
    fun isFavorite(id: Int): Boolean {
        return favorites.value.any { it?.id == id }
    }
}