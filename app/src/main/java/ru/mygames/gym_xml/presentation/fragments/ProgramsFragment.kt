package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.mygames.gym_xml.FavoriteViewModel
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.Workout
import ru.mygames.gym_xml.WorkoutAdapterFullScreen
import ru.mygames.gym_xml.WorkoutViewModel
import ru.mygames.gym_xml.toFavoriteExercise
import javax.inject.Inject

@AndroidEntryPoint
class ProgramsFragment @Inject constructor(
    private val favoriteViewModel: FavoriteViewModel,
    private val workoutViewModel: WorkoutViewModel
) : Fragment() {
    private lateinit var adapter: WorkoutAdapterFullScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_programs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация адаптера с передачей метода для обработки нажатия на иконку лайка
        adapter = WorkoutAdapterFullScreen(requireContext(), ::onLikeClick)

        // Подключаем RecyclerView
        setupRecyclerView(view)

        // Наблюдаем за тренировками
        workoutViewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            adapter.submitList(workouts)
        }

        // Загружаем тренировки по умолчанию
        workoutViewModel.loadWorkouts()

        // Подключаем наблюдатель для избранных тренировок
        lifecycleScope.launchWhenStarted {
            favoriteViewModel.favorites.collect { favorites ->
                adapter.updateFavorites(favorites)
            }
        }

    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    // Метод, который будет вызываться при нажатии на иконку лайка
    private fun onLikeClick(workout: Workout, isFavorite: Boolean) {
        val favoriteExercise = workout.toFavoriteExercise() // Преобразуем Workout в FavoriteExercise
        if (isFavorite) {
            favoriteViewModel.removeFavorite(favoriteExercise)
        } else {
            favoriteViewModel.addFavorite(favoriteExercise)
        }
    }
}