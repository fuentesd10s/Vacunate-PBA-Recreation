package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentStartBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var b: FragmentStartBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b = FragmentStartBinding.bind(view)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                view?.post {
                    findNavController().navigate(StartFragmentDirections.actionStartFragmentToSignUpFragment())
                }
            }, 3000
        )

    }
}