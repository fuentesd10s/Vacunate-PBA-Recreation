package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentCitizenshipNumberBinding
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.ScannerConfig

class CitizenshipNumberFragment : Fragment(R.layout.fragment_citizenship_number) {

    private lateinit var b: FragmentCitizenshipNumberBinding
    private val scanCustomCode = registerForActivityResult(ScanCustomCode(), ::handleResult)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentCitizenshipNumberBinding.bind(view)

        setupBtnHowToGetCitizenshipNumber()

        setupTilCitizenshipNumber()

        b.btnSentCitizenshipNumber.setOnClickListener {
            navigateAction()
        }
    }

    private fun navigateAction() {
        b.progressBar.show()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.post {
                    findNavController().navigate(CitizenshipNumberFragmentDirections.actionCitizenshipNumberFragmentToHomeFragment())
                    b.progressBar.hide()
                }
            }, 2000
        )
    }

    private fun setupTilCitizenshipNumber() {
        b.tilCitizenshipNumber.setEndIconOnClickListener {
            scanCustomCode.launch(
                ScannerConfig.build {
                    setOverlayStringRes(R.string.app_name)
                    setOverlayDrawableRes(R.drawable.ic_camera)
                    setShowTorchToggle(true)
                }
            )
        }

        b.etCitizenshipNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                b.btnSentCitizenshipNumber.isEnabled = p0?.length == 10
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun setupBtnHowToGetCitizenshipNumber() {
        b.btnHowToGetCitizenshipNumber.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setView(
                    ImageView(requireContext()).apply {
                        this.setImageResource(R.drawable.citizenship_number)
                        this.adjustViewBounds = true
                    })
                .setPositiveButton("Ok") { d, _ ->
                    d.dismiss()
                }
                .show()
        }
    }

    private fun handleResult(qrResult: QRResult) {
        when (qrResult){
            is QRResult.QRSuccess ->{
                Log.d("GONZA","QR DETECTED")
                b.etCitizenshipNumber.setText(qrResult.content.rawValue)
            }
            QRResult.QRUserCanceled -> {
                Log.d("GONZA","QR CANCELED")
            }
            QRResult.QRMissingPermission -> {
                Log.d("GONZA","QR MISSING PERMISSION")
            }
            is QRResult.QRError -> {
                Log.d("GONZA","QR ERROR")
            }
        }
    }
}