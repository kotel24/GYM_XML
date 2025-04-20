package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.WorkoutAdapter
import ru.mygames.gym_xml.WorkoutViewModel

class AccountFragment : Fragment() {
    private lateinit var viewModel: WorkoutViewModel
    private lateinit var adapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = WorkoutAdapter { }
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        view.findViewById<ImageView>(R.id.tv_count).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.i("SEARCH", viewModel.repository.searchExercise("g").toString())
            }
        }

        setupRecyclerView(view)
        setupObservers()
        viewModel.loadWorkouts()
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.workoutReView)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            adapter.submitList(workouts)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Показать/скрыть ProgressBar
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let { showError(it) }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_LONG).show()
    }
}