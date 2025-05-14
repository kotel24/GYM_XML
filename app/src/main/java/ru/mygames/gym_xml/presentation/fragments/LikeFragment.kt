package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mygames.gym_xml.presentation.adapters.FavoriteAdapter
import ru.mygames.gym_xml.presentation.viewModel.FavoriteViewModel
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.data.toFavoriteExercise
import ru.mygames.gym_xml.data.toWorkout
import javax.inject.Inject

class LikeFragment @Inject constructor(
    private val favoriteViewModel: FavoriteViewModel
) : Fragment() {

    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_programs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация адаптера
        adapter = FavoriteAdapter(
            context = requireContext(),
            onLikeClick = { workout, shouldAdd ->
                val fav = workout.toFavoriteExercise()
                if (shouldAdd) {
                    favoriteViewModel.addFavorite(fav)
                } else {
                    favoriteViewModel.removeFavorite(fav)
                }
            }
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Подписка на Flow избранных упражнений
        viewLifecycleOwner.lifecycleScope.launch {
            favoriteViewModel.favorites.collectLatest { favorites ->
                val workouts = favorites.filterNotNull().map { it.toWorkout() }
                adapter.submitList(workouts)
                adapter.updateFavorites(favorites.filterNotNull())
            }
        }
    }
}