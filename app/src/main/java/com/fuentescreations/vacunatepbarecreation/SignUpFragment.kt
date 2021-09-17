package com.fuentescreations.vacunatepbarecreation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var b:FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentSignUpBinding.bind(view)

        b.btnHowToGetTramitNumber.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("El número de trámite se encuentra en el frente o dorso de tu documento.")
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
    }
}