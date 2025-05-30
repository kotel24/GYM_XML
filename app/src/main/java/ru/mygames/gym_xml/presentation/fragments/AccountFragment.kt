package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.data.toFavoriteExercise
import ru.mygames.gym_xml.domain.workout.Workout
import ru.mygames.gym_xml.presentation.adapters.WorkoutAdapter
import ru.mygames.gym_xml.presentation.viewModel.FavoriteViewModel
import ru.mygames.gym_xml.presentation.viewModel.WorkoutViewModel
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment @Inject constructor(
    private val favoriteViewModel: FavoriteViewModel,
    private val workoutViewModel: WorkoutViewModel
) : Fragment() {

    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация адаптера с передачей метода для обработки нажатия на иконку лайка
        adapter = WorkoutAdapter(requireContext(), ::onLikeClick)

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

        val nameTxt = view.findViewById<TextView>(R.id.nameTxt)
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

        val dbRef = FirebaseDatabase.getInstance().getReference("USER/users/$uid")

        dbRef.get().addOnSuccessListener { snapshot ->
            val name = snapshot.child("name").value as? String
            nameTxt.text = name ?: "Athlete"
        }.addOnFailureListener {
            nameTxt.text = "Athlete"
        }

        // Поиск по имени тренировки при нажатии Enter в EditText
        val edTxt = view.findViewById<EditText>(R.id.workoutEditTxt)
        edTxt.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                workoutViewModel.loadWorkoutsName(name = edTxt.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.workoutReView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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