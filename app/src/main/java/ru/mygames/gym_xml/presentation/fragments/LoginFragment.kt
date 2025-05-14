package ru.mygames.gym_xml.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.mygames.gym_xml.domain.Users
import ru.mygames.gym_xml.databinding.ActivityLoginBinding
import ru.mygames.gym_xml.presentation.activity.MainActivity

class LoginFragment : Fragment() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val dbRef = database.getReference("USER")

        binding.regButton.setOnClickListener {
            val name = binding.signUpName.text.toString().trim()
            val email = binding.signUpEmail.text.toString().trim()
            val surname = binding.signUpSurname.text.toString().trim()
            val password = binding.signUpPassword.text.toString()
            val repPassword = binding.signUpRepPassword.text.toString()

            // Проверка на пустые поля
            if (name.isEmpty() || email.isEmpty() || surname.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
                if (name.isEmpty()) binding.signUpName.error = "Input name"
                if (email.isEmpty()) binding.signUpEmail.error = "Input email"
                if (surname.isEmpty()) binding.signUpSurname.error = "Input surname"
                if (password.isEmpty()) binding.signUpPassword.error = "Input password"
                if (repPassword.isEmpty()) binding.signUpRepPassword.error = "Input password"
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!email.matches(emailPattern.toRegex())) {
                binding.signUpEmail.error = "Некорректный email"
                Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.signUpPassword.error = "Пароль должен содержать минимум 6 символов"
                Toast.makeText(requireContext(), "Слишком короткий пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != repPassword) {
                binding.signUpRepPassword.error = "Пароли не совпадают"
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase регистрация
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val userId = auth.currentUser!!.uid
                    val user = Users(name, surname, email, userId)
                    dbRef.child("users").child(userId).setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                        } else {
                            Toast.makeText(requireContext(), "Ошибка при сохранении данных", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Ошибка регистрации: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}