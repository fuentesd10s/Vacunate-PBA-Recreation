package com.fuentescreations.vacunatepbarecreation

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.vacunatepbarecreation.adapters.AdapterItemVaccineDate
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentSummaryBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.models.getExample
import java.text.SimpleDateFormat
import java.util.*

class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private lateinit var b:FragmentSummaryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentSummaryBinding.bind(view)

        setupBtnTimer()
        setupRv()
    }

    private fun setupRv() {
        val vaccineDateList = mutableListOf<ModelVaccineDate>()
        for (i in 1.. 10){
            vaccineDateList.add(ModelVaccineDate().getExample())
        }
        b.rvVaccineDates.adapter= AdapterItemVaccineDate(vaccineDateList)
    }

    private fun setupBtnTimer() {
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