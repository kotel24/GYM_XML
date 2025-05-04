package ru.mygames.gym_xml

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.mygames.gym_xml.databinding.ActivityLoginBinding
import ru.mygames.gym_xml.databinding.ActivityMainBinding
import ru.mygames.gym_xml.presentation.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val dbRef = database.getReference("USER")
        loginBinding.regButton.setOnClickListener {
            val name = loginBinding.signUpName.text.toString()
            val email = loginBinding.signUpEmail.text.toString()
            val phone = loginBinding.signUpPhone.text.toString()
            val password = loginBinding.signUpPassword.text.toString()
            val repPassword = loginBinding.signUpRepPassword.text.toString()
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
                if (name.isEmpty())
                    loginBinding.signUpName.error = "input is incorrect"
                if (email.isEmpty())
                    loginBinding.signUpEmail.error = "input is incorrect"
                if (phone.isEmpty())
                    loginBinding.signUpPhone.error = "input is incorrect"
                if (password.isEmpty())
                    loginBinding.signUpPassword.error = "input is incorrect"
                if (repPassword.isEmpty())
                    loginBinding.signUpRepPassword.error = "input is incorrect"
                Toast.makeText(this, "not correct", Toast.LENGTH_LONG).show()
            } else if (!email.matches(emailPattern.toRegex()))
                TODO()
            else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val database =
                            dbRef.child("users").child(auth.currentUser!!.uid)
                        val users = Users(name, email, phone, auth.currentUser!!.uid)
                        database.setValue(users).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else
                                Toast.makeText(this, "Что то пошло не так", Toast.LENGTH_LONG)
                                    .show()
                        }
                    }
                }
            }
        }
    }
}

































