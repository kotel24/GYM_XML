package ru.mygames.gym_xml.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mygames.gym_xml.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var ExerciesItemContainer:FragmentContainerView? = null
    private lateinit var exerciesAdapter: ExerciesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.exercieslist.observe(this){
            exerciesAdapter.submitList(it)
        }
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener {
            val intent = ExerciesItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }
    }
    private fun setupRecyclerView() {
        val rvExerciesList = findViewById<RecyclerView>(R.id.recyclerView)
        exerciesAdapter = ExerciesAdapter()
        with(rvExerciesList) {
            adapter = exerciesAdapter
            recycledViewPool.setMaxRecycledViews(
                ExerciesAdapter.VIEW_TYPE_ENABLED,
                ExerciesAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ExerciesAdapter.VIEW_TYPE_DISABLED,
                ExerciesAdapter.MAX_POOL_SIZE
            )
        }
        setupOnLongClickListener()
        setupOnClickLictener()
        setupSwipeListener(rvExerciesList)
    }
    private fun setupSwipeListener(rvExerciesList: RecyclerView?) {
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
                val item = exerciesAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteExercises(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvExerciesList)
    }

    private fun setupOnClickLictener() {
        exerciesAdapter.onExerciesItemClickListener =
            {
                val intent = ExerciesItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            }
    }

    private fun setupOnLongClickListener() {
        exerciesAdapter.onExerciesItemLongClickListener = {
            viewModel.editExercises(it)
        }
    }
}