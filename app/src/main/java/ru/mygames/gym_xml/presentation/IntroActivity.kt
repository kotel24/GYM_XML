package ru.mygames.gym_xml.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ru.mygames.gym_xml.LoginActivity
import ru.mygames.gym_xml.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var iBinding: ActivityIntroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iBinding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(iBinding.root)

        // Инициализация Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Обработка нажатия на кнопку Let's Go
        iBinding.startButton.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // Пользователь уже вошел в систему
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Пользователь не вошел — переход на экран логина/регистрации
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        // Обработка нажатия на текст "Sign In"
        iBinding.signInTxt.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}