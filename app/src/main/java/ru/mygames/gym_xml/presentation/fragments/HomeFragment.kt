package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.presentation.ExerciesAdapter
import ru.mygames.gym_xml.presentation.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var exerciesListAdapter: ExerciesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        setupRecyclerView(view)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.exercieslist.observe(this){
            exerciesListAdapter.submitList(it)
        }
        val exerciesFragment = ExerciesFragment()
        val buttonAddShopItem = view.findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddShopItem.setOnClickListener{
            val exerciesFragment = ExerciesFragment.newInstanceAddItem()
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment, exerciesFragment)
            transaction.commit()
        }

    }


    private fun setupRecyclerView(view: View) {
        val rvShoplist = view.findViewById<RecyclerView>(R.id.recyclerView)
        exerciesListAdapter = ExerciesAdapter()
        with(rvShoplist) {
            adapter = exerciesListAdapter
            recycledViewPool.setMaxRecycledViews(
                ru.mygames.gym_xml.presentation.ExerciesAdapter.VIEW_TYPE_ENABLED,
                ru.mygames.gym_xml.presentation.ExerciesAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ru.mygames.gym_xml.presentation.ExerciesAdapter.VIEW_TYPE_DISABLED,
                ru.mygames.gym_xml.presentation.ExerciesAdapter.MAX_POOL_SIZE
            )
        }
        setupOnLongClickListener()
        setupOnClickLictener()
        setupSwipeListener(rvShoplist)
    }

    private fun setupSwipeListener(rvShoplist: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = exerciesListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteExercises(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShoplist)
    }

    private fun setupOnClickLictener() {
        exerciesListAdapter.onExerciesItemClickListener = {
            val exerciesFragment = ExerciesFragment.newInstanceEditItem(it.id)
            val transaction : FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.fragment, exerciesFragment)
            transaction.commit()
        }
    }

    private fun setupOnLongClickListener() {
        exerciesListAdapter.onExerciesItemLongClickListener = {
            viewModel.editExercises(it)
        }
    }
}