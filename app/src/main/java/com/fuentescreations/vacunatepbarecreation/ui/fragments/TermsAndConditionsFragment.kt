package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentTermsAndConditionsBinding

class TermsAndConditionsFragment : Fragment(R.layout.fragment_terms_and_conditions) {

    private lateinit var b:FragmentTermsAndConditionsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentTermsAndConditionsBinding.bind(view)
    }
}