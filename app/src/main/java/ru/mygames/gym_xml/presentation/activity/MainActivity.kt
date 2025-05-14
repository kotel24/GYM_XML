package ru.mygames.gym_xml.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.mygames.gym_xml.presentation.viewModel.FavoriteViewModel
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.presentation.viewModel.WorkoutViewModel
import ru.mygames.gym_xml.databinding.ActivityMainBinding
import ru.mygames.gym_xml.presentation.fragments.AccountFragment
import ru.mygames.gym_xml.presentation.fragments.LikeFragment
import ru.mygames.gym_xml.presentation.fragments.HomeFragment
import ru.mygames.gym_xml.presentation.fragments.ProgramsFragment
import ru.mygames.gym_xml.presentation.fragments.TimerFragment
import ru.mygames.gym_xml.presentation.viewModel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val dataModel: MainViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val workoutViewModel: WorkoutViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountFragment = AccountFragment(favoriteViewModel, workoutViewModel)
        val homeFragment = HomeFragment()
        val programsFragment = ProgramsFragment(favoriteViewModel, workoutViewModel)
        val timerFragment = TimerFragment()
        val likeFragment = LikeFragment(favoriteViewModel)

        setCurrentFragment(accountFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.programs -> setCurrentFragment(programsFragment)
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.profile -> setCurrentFragment(accountFragment)
                R.id.timer -> setCurrentFragment(timerFragment)
                R.id.like -> setCurrentFragment(likeFragment)

            }
            true
        }
        dataModel

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            commit()
        }

}