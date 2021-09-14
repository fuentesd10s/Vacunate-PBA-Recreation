package com.fuentescreations.vacunatepbarecreation

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private lateinit var b:FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentSummaryBinding.bind(view)

        setBtnTimer()
    }

    private fun setBtnTimer() {
        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(p0: Long) {
                b.btnRequest.text = p0.toString()
            }

            override fun onFinish() {

            }

        }
        timer.start()
    }
}