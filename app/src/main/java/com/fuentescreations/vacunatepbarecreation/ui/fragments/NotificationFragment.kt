package com.fuentescreations.vacunatepbarecreation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.FragmentNotificationBinding
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show

class NotificationFragment : Fragment(R.layout.fragment_notification) {

    private lateinit var b: FragmentNotificationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        b = FragmentNotificationBinding.bind(view)

        setupSwitchNotification()

        setupCheckBoxes()
    }

    private fun setupCheckBoxes() {
        b.cbSelectAllMessages.setOnCheckedChangeListener { _, boolean ->
            b.cbMessage.isChecked = boolean
            deleteIconVisibility(boolean)
        }

        b.cbMessage.setOnCheckedChangeListener { _, boolean ->
            deleteIconVisibility(boolean)
        }
    }

    private fun deleteIconVisibility(boolean: Boolean){
        if (boolean) b.ivDelete.show()
        else b.ivDelete.hide()
    }

    private fun setupSwitchNotification() {
        if (b.swNotification.isChecked) {
            b.rvNotification.show()
            b.lyNotificationDisabled.hide()
            b.lyTopNotification.show()
        } else {
            b.rvNotification.hide()
            b.lyNotificationDisabled.show()
            b.lyTopNotification.hide()
        }

        b.swNotification.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                b.lyNotificationDisabled.hide()
                b.lyTopNotification.show()
            } else {
                b.lyTopNotification.hide()
                b.lyNotificationDisabled.show()
            }
        }
    }
}