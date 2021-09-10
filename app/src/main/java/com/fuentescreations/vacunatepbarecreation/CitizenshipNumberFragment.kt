package com.fuentescreations.vacunatepbarecreation

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentCitizenshipNumberBinding

class CitizenshipNumberFragment : Fragment(R.layout.fragment_citizenship_number){

    private lateinit var b:FragmentCitizenshipNumberBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentCitizenshipNumberBinding.bind(view)

        b.lyTramitNumberCitizenshipNumber.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setView(
                    ImageView(requireContext()).apply {
                        this.setImageResource(R.drawable.dni)
                        this.adjustViewBounds = true
                    })
                .setPositiveButton("Ok") { d, _ ->
                    d.dismiss()
                }
                .show()
        }



        b.tilCitizenshipNumber.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "Click!!", Toast.LENGTH_SHORT).show()
        }

        b.etCitizenshipNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                b.btnSentCitizenshipNumber.isEnabled = p0?.length==10
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }
}