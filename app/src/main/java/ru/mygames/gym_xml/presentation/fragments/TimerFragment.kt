package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTimerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // access the chronometer from XML file
        val meter = view.findViewById<Chronometer>(R.id.c_meter)
        //access the button using id
        val btnStart = view.findViewById<FloatingActionButton>(R.id.bStart)
        val btnStop = view.findViewById<FloatingActionButton>(R.id.bStop)
        val btnReset = view.findViewById<FloatingActionButton>(R.id.bReset)
        btnStart?.setOnClickListener {
            meter.base = SystemClock.elapsedRealtime()
            meter.start()
        }
        btnStop?.setOnClickListener {
            meter.stop()
        }

        btnReset.setOnClickListener {
            meter.stop()
            meter.base = SystemClock.elapsedRealtime()
        }
    }
}
