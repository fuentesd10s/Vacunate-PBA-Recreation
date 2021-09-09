package com.fuentescreations.vacunatepbarecreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentCitizenshipNumberBinding

class CitizenshipNumberFragment : Fragment(R.layout.fragment_citizenship_number){

    private lateinit var b:FragmentCitizenshipNumberBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentCitizenshipNumberBinding.bind(view)
    }
}