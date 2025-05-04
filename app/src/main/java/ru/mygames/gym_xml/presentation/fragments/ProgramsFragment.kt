package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.WorkoutAdapter
import ru.mygames.gym_xml.WorkoutAdapterFullScreen
import ru.mygames.gym_xml.WorkoutViewModel

class ProgramsFragment : Fragment() {
    private lateinit var viewModel: WorkoutViewModel
    private lateinit var adapter: WorkoutAdapterFullScreen

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = WorkoutAdapterFullScreen(requireContext()) { }
        return inflater.inflate(R.layout.fragment_programs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        setupObservers()
        viewModel.loadWorkouts()
    }


    private fun setupObservers() {
        viewModel.workouts.observe(viewLifecycleOwner) { workouts ->
            adapter.submitList(workouts)
        }
    }
}