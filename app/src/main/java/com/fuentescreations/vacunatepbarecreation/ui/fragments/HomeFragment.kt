package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.app.AlertDialog
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
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import android.net.Uri
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment(R.layout.fragment_home),
    AdapterItemVaccineDate.ItemVaccineClickListener {

    private lateinit var b: FragmentHomeBinding
    private lateinit var mAdapter: AdapterItemVaccineDate
    val TAG = "GONZA"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentHomeBinding.bind(view)

        setupBtnTimer()
        setupRv()
    }

    private fun setupRv() {
        val vaccineDateList = mutableListOf<ModelVaccineDate>()

        for (i in 1..3) vaccineDateList.add(ModelVaccineDate().getExample())

        mAdapter = AdapterItemVaccineDate(vaccineDateList, ClassesEnum.Home, this)
        b.rvAttendedVaccineDates.adapter = mAdapter
    }

    private fun setupBtnTimer() {
        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                b.btnRequest.text = "Podrás consultar en ${getDate(p0)}"
            }

            override fun onFinish() {
                b.btnRequest.isEnabled = true
                b.btnRequest.text = "Consultar nuevamente"
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

    override fun onItemVaccineShortClickListener() {
        val navView = activity?.findViewById<NavigationView>(R.id.navView)

        navView?.menu?.getItem(3)?.onNavDestinationSelected(findNavController())
    }

    override fun onItemVaccineLocationLister(latLng: LatLng) {
        AlertDialog.Builder(requireContext())
            .setTitle("¿Deseas abrir Google Maps con la ubicación del centro vacunatorio?")
            .setPositiveButton("Abrir") { d, _ ->
                val mapUri =
                    Uri.parse("geo:0,0?q=${latLng.latitude},${latLng.longitude}(Vacunatorio)")
                val mapIntent = Intent(Intent.ACTION_VIEW, mapUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                requireActivity().startActivity(mapIntent)
                d.dismiss()
            }
            .setNegativeButton("Cancelar") { d, _ ->
                d.dismiss()
            }
            .show()


    }

    override fun onItemVaccineCancelDate(modelVaccineDate: ModelVaccineDate) {
        AlertDialog.Builder(requireContext())
            .setTitle("¿Estas seguro que quieres cancelar tu turno?")
            .setPositiveButton("Confirmar") { d, _ ->
                modelVaccineDate.status = VaccineDateStatus.CANCELLED
                mAdapter.notifyDataSetChanged()

                d.dismiss()
            }
            .setNegativeButton("Volver") { d, _ ->
                d.dismiss()
            }
            .show()
    }
}