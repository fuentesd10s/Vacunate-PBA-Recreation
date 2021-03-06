package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentSignUpBinding
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var b: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentSignUpBinding.bind(view)

        setupBtnHowToGetTramitNumber()
        setupGendersButtons()
        setupTermsConditionsCheckBox()
        setupPressBackButton()

        b.btnSignIn.setOnClickListener {
            navigateAction()
        }
    }

    private fun navigateAction() {
        b.progressBar.show()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.post {
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToCitizenshipNumberFragment())
                    b.progressBar.hide()
                }
            }, 2000
        )

    }

    private fun setupPressBackButton() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupTermsConditionsCheckBox() {
        b.cbTermsConditions.setOnCheckedChangeListener { _, boolean ->
            b.btnSignIn.isEnabled = boolean
        }
    }

    private fun setupGendersButtons() {
        b.lyMaleSignUp.setOnClickListener {
            b.rbFemaleSignUp.isChecked = false
            b.rbMaleSignUp.isChecked = true

            b.ivFemaleIcon.setColorFilter(ContextCompat.getColor(requireContext(),R.color.md_blue_grey_700))
            b.tvFemale.setTextColor(ContextCompat.getColor(requireContext(),R.color.md_blue_grey_600))

            b.tvMale.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_accent))
            b.ivMaleIcon.setColorFilter(ContextCompat.getColor(requireContext(),R.color.color_accent))
        }

        b.lyFemaleSignUp.setOnClickListener {
            b.rbFemaleSignUp.isChecked = true
            b.rbMaleSignUp.isChecked = false

            b.ivFemaleIcon.setColorFilter(ContextCompat.getColor(requireContext(),R.color.color_accent))
            b.tvFemale.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_accent))

            b.tvMale.setTextColor(ContextCompat.getColor(requireContext(),R.color.md_blue_grey_600))
            b.ivMaleIcon.setColorFilter(ContextCompat.getColor(requireContext(),R.color.md_blue_grey_700))
        }
    }

    private fun setupBtnHowToGetTramitNumber() {
        b.btnHowToGetTramitNumber.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setMessage("El n??mero de tr??mite se encuentra en el frente o dorso de tu documento.")
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