package com.fuentescreations.vacunatepbarecreation

import android.icu.text.MessageFormat.format
import android.os.Bundle
import android.os.CountDownTimer
import android.text.format.DateFormat.format
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentSummaryBinding
import java.lang.String.format
import java.text.DateFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
import java.util.*

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private lateinit var b:FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentSummaryBinding.bind(view)

        setBtnTimer()
    }

    private fun setBtnTimer() {
        val timer = object: CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                b.btnRequest.text = "Podrás consultar en ${getDate(p0)}"
            }

            override fun onFinish() {
                b.btnRequest.isEnabled = true
                b.btnRequest.text = "Consultar"
            }

        }
        timer.start()
    }

    fun getDate(milliSeconds: Long): String {
        val calendar = Calendar.getInstance().apply {
            this.timeInMillis = milliSeconds
        }

        return SimpleDateFormat("mm:ss").format(calendar.time)
    }
}