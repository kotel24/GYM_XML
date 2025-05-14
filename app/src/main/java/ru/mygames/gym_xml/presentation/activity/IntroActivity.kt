package ru.mygames.gym_xml.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mygames.gym_xml.presentation.fragments.IntroFragment
import ru.mygames.gym_xml.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, IntroFragment())
                .commit()
        }
    }
}