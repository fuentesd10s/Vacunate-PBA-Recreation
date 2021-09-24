package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.adapters.AdapterItemVaccineDate
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentHomeBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.models.getExample
import com.fuentescreations.vacunatepbarecreation.utils.ClassesEnum
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home),
    AdapterItemVaccineDate.ItemVaccineClickListener {

    private lateinit var b: FragmentHomeBinding
    val TAG="GONZA"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentHomeBinding.bind(view)

        setupBtnTimer()
        setupRv()
    }

    private fun setupRv() {
        val vaccineDateList = mutableListOf<ModelVaccineDate>()
        for (i in 1..3) {
            vaccineDateList.add(ModelVaccineDate().getExample())
        }
        b.rvAttendedVaccineDates.adapter = AdapterItemVaccineDate(vaccineDateList, ClassesEnum.Home,this)
    }

    private fun setupBtnTimer() {
        val timer = object : CountDownTimer(10000, 1000) {
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

    override fun onItemVaccineShortClickListener(modelVaccineDate: ModelVaccineDate) {
        Log.d(TAG, "onItemVaccineShortClickListener: ${modelVaccineDate.toString()}")
    }

    override fun onItemVaccineLocationLister(latLng: LatLng) {
        Log.d(TAG, "onItemVaccineLocationLister: ${latLng.toString()}")
    }

    override fun onItemVaccineCancelDate() {
        Log.d(TAG, "onItemVaccineCancelDate: CANCEL TOUCHED")
    }
}