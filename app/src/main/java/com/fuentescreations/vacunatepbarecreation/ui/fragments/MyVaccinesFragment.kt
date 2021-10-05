package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.adapters.AdapterItemVaccineDate
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentMyVaccinesBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.models.getExample
import com.fuentescreations.vacunatepbarecreation.utils.ClassesEnum
import com.google.android.gms.maps.model.LatLng

class MyVaccinesFragment : Fragment(R.layout.fragment_my_vaccines),
    AdapterItemVaccineDate.ItemVaccineClickListener {

    private lateinit var b:FragmentMyVaccinesBinding
    private lateinit var mAdapter: AdapterItemVaccineDate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentMyVaccinesBinding.bind(view)

        setupRv()
    }

    private fun setupRv() {
        val vaccineDateList = mutableListOf<ModelVaccineDate>()

        for (i in 1..3) vaccineDateList.add(ModelVaccineDate().getExample())

        mAdapter = AdapterItemVaccineDate(vaccineDateList, this.javaClass.name, this)
        b.rvMyVaccines.adapter = mAdapter
    }

    override fun onItemVaccineShortClickListener() {
    }

    override fun onItemVaccineLocationLister(latLng: LatLng) {
    }

    override fun onItemVaccineCancelDate(modelVaccineDate: ModelVaccineDate) {
    }
}