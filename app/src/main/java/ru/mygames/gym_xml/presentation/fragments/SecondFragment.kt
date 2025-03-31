package ru.mygames.gym_xml.presentation.fragments

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import ru.mygames.gym_xml.R
import ru.mygames.gym_xml.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // access the chronometer from XML file
        val meter = view.findViewById<Chronometer>(R.id.c_meter)
        //access the button using id
        val btn = view.findViewById<Button>(R.id.bTimer)
        btn?.setOnClickListener(object : View.OnClickListener {

            var isWorking = false

            override fun onClick(v: View) {
                if (!isWorking) {
                    meter.base = SystemClock.elapsedRealtime()
                    meter.start()
                    btn.text = "Stop"
                    isWorking = true
                } else {
                    meter.stop()
                    btn.text = "Start"
                    isWorking = false
                }
            }
        })
        view.findViewById<Button>(R.id.bresume).setOnClickListener {
            meter.stop()
            btn.text = "Start"
            meter.base = SystemClock.elapsedRealtime()

        }
    }
}
