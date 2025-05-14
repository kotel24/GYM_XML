package ru.mygames.gym_xml.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.databinding.FragmentIntroBinding
import ru.mygames.gym_xml.presentation.activity.MainActivity


class IntroFragment : Fragment() {

    private var _binding: FragmentIntroBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIntroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        binding.startButton.setOnClickListener {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                // Пользователь уже вошел — переход в MainActivity
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                // Переход на LoginFragment
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container, // контейнер, куда грузится фрагмент
                        LoginFragment()
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.signInTxt.setOnClickListener {
            // Также переход на LoginFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}