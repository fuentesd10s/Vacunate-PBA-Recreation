package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentMyDataBinding

class MyDataFragment : Fragment(R.layout.fragment_my_data) {

    private lateinit var b:FragmentMyDataBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentMyDataBinding.bind(view)

        b.btnGoToPersonalData.setOnClickListener {
            findNavController().navigate(MyDataFragmentDirections.actionMyAccountFragmentToPersonalDataFragment())
        }
    }
}