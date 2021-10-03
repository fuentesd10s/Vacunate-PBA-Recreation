package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.BottomSheetDialogIdentityBinding
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentPersonalDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class PersonalDataFragment : Fragment(R.layout.fragment_personal_data) {

    private lateinit var b: FragmentPersonalDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentPersonalDataBinding.bind(view)

        b.etIdentity.setOnClickListener {
            setupBottomSheetDialog()
        }
    }

    private fun setupBottomSheetDialog() {
        val binding = BottomSheetDialogIdentityBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(binding.root)

        dialog.show()
    }
}