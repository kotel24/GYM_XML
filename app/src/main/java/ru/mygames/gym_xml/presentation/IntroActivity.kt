package ru.mygames.gym_xml.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mygames.gym_xml.LoginActivity
import ru.mygames.gym_xml.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var iBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(iBinding.root)
//        val database = Firebase.database
//        val myRef = database.getReference("message")
//
//        myRef.setValue("Hello, World!")
        iBinding.startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        iBinding.signInTxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}