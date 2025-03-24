package ru.mygames.gym_xml.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.databinding.ActivityMainBinding
import ru.mygames.gym_xml.presentation.fragments.AccountFragment
import ru.mygames.gym_xml.presentation.fragments.CaloriesFragment
import ru.mygames.gym_xml.presentation.fragments.ExerciesFragment
import ru.mygames.gym_xml.presentation.fragments.HomeFragment
import ru.mygames.gym_xml.presentation.fragments.ProgramsFragment
import ru.mygames.gym_xml.presentation.fragments.TimerFragment

class MainActivity : AppCompatActivity() {
    private val dataModel:MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountFragment = AccountFragment()
        val homeFragment = HomeFragment()
        val programsFragment = ProgramsFragment()
        val timerFragment = TimerFragment()
        val caloriesFragment = CaloriesFragment()

        setCurrentFragment(accountFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.programs -> setCurrentFragment(programsFragment)
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.profile -> setCurrentFragment(accountFragment)
                R.id.timer -> setCurrentFragment(timerFragment)
                R.id.calories -> setCurrentFragment(caloriesFragment)

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