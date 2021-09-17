package com.fuentescreations.vacunatepbarecreation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.ItemVaccineDateBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus
import java.text.SimpleDateFormat
import java.util.*

class AdapterItemVaccineDate(private val vaccineDateList: List<ModelVaccineDate>) :
    RecyclerView.Adapter<AdapterItemVaccineDate.ViewHolderVaccineDate>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVaccineDate {
        val b = ItemVaccineDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolderVaccineDate(b)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolderVaccineDate, position: Int) {
        holder.bind(vaccineDateList[position])
    }

    override fun getItemCount(): Int = vaccineDateList.size

    inner class ViewHolderVaccineDate(private val b: ItemVaccineDateBinding) :
        RecyclerView.ViewHolder(b.root) {
        private val mContext = itemView.context

        fun bind(modelVaccineDate: ModelVaccineDate) {

            b.chipVaccineStatus.text = modelVaccineDate.status.value
            b.tvVaccineDose.text = modelVaccineDate.dosesNumber.value
            b.tvHospitalName.text = modelVaccineDate.hospitalName
            b.tvLocation.text = modelVaccineDate.address
            b.tvDate.text = modelVaccineDate.date?.let { getDateFromMillis(it) }

            when (modelVaccineDate.status) {
                VaccineDateStatus.ATTENDED -> {
                    b.chipVaccineStatus.chipBackgroundColor =
                        ColorStateList.valueOf(getColor(R.color.md_blue_100))

                    b.chipVaccineStatus.chipIconTint = ColorStateList.valueOf(
                        getColor(R.color.md_blue_900)
                    )

                    b.chipVaccineStatus.setTextColor(
                        getColor(R.color.md_blue_900)
                    )

                    b.statusAttendedOrCanceled.visibility = View.GONE
                }
                VaccineDateStatus.PENDING -> {
                    b.chipVaccineStatus.chipBackgroundColor = ColorStateList.valueOf(
                        getColor(R.color.md_orange_100)
                    )
                    b.chipVaccineStatus.chipIconTint = ColorStateList.valueOf(
                        getColor(R.color.md_orange_900)
                    )

                    b.chipVaccineStatus.setTextColor(
                        getColor(R.color.md_orange_900)
                    )
                }
                VaccineDateStatus.CANCELLED -> {
                    b.chipVaccineStatus.chipBackgroundColor = ColorStateList.valueOf(
                        getColor(R.color.md_red_100)
                    )
                    b.chipVaccineStatus.chipIconTint = ColorStateList.valueOf(
                        getColor(R.color.md_red_900)
                    )
                    b.chipVaccineStatus.setTextColor(
                        getColor(R.color.md_red_900)
                    )

                    b.statusAttendedOrCanceled.visibility = View.GONE
                }
                VaccineDateStatus.NOT_AVAILABLE -> {
                    b.chipVaccineStatus.chipBackgroundColor = ColorStateList.valueOf(
                        getColor(R.color.md_grey_100)
                    )
                    b.chipVaccineStatus.chipIconTint = ColorStateList.valueOf(
                        getColor(R.color.md_grey_700)
                    )

                    b.chipVaccineStatus.setTextColor(
                        getColor(R.color.md_grey_700)
                    )
                    b.statusNotAvailable.visibility = View.GONE
                    b.tvStatusNotAvailable.visibility = View.VISIBLE
                }
            }
        }

        private fun getColor(color: Int): Int = ContextCompat.getColor(mContext, color)

        private fun getDateFromMillis(milliSeconds: Long): String {
            val calendar = Calendar.getInstance().apply {
                this.timeInMillis = milliSeconds
            }

            return SimpleDateFormat("dd/MM/yyyy mm:ss").format(calendar.time)
        }
    }
}