package com.fuentescreations.vacunatepbarecreation

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentCitizenshipNumberBinding
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.ScannerConfig

class CitizenshipNumberFragment : Fragment(R.layout.fragment_citizenship_number) {

    private lateinit var b: FragmentCitizenshipNumberBinding
    val scanCustomCode = registerForActivityResult(ScanCustomCode(), ::handleResult)

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
            scanCustomCode.launch(
                ScannerConfig.build {
                    setOverlayStringRes(R.string.app_name) // string resource used for the scanner overlay
                    setOverlayDrawableRes(R.drawable.ic_camera) // drawable resource used for the scanner overlay
                    setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
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

    private fun handleResult(qrResult: QRResult) {
        when (qrResult){
            is QRResult.QRSuccess ->{
                Log.d("GONZA","QR DETECTED")
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