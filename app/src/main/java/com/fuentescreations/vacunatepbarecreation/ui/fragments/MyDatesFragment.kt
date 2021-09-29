package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.adapters.AdapterItemVaccineDate
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentMyDatesBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.models.getExample
import com.fuentescreations.vacunatepbarecreation.utils.ClassesEnum
import com.google.android.gms.maps.model.LatLng

class MyDatesFragment : Fragment(R.layout.fragment_my_dates),
    AdapterItemVaccineDate.ItemVaccineClickListener {

    private lateinit var b: FragmentMyDatesBinding
    private lateinit var mAdapter: AdapterItemVaccineDate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentMyDatesBinding.bind(view)

        setupRv()
    }

    private fun setupRv() {
        val vaccineDateList = mutableListOf<ModelVaccineDate>()

        for (i in 1..3) vaccineDateList.add(ModelVaccineDate().getExample())

        mAdapter = AdapterItemVaccineDate(vaccineDateList, ClassesEnum.MyDates, this)
        b.rvMyDates.adapter = mAdapter
    }

    override fun onItemVaccineShortClickListener() {
    }

    override fun onItemVaccineLocationLister(latLng: LatLng) {
    }

    override fun onItemVaccineCancelDate(modelVaccineDate: ModelVaccineDate) {
    }
}