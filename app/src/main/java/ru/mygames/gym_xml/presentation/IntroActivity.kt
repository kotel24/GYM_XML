package ru.mygames.gym_xml.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.databinding.ActivityIntroBinding
import ru.mygames.gym_xml.databinding.ActivityMainBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var iBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(iBinding.root)
        iBinding.startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}