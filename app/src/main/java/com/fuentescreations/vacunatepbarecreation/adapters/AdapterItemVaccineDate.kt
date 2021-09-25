package com.fuentescreations.vacunatepbarecreation.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.ItemVaccineDateBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.utils.ClassesEnum
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus
import com.fuentescreations.vacunatepbarecreation.utils.hide
import com.fuentescreations.vacunatepbarecreation.utils.show
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.util.*

class AdapterItemVaccineDate(
    private val vaccineDateList: List<ModelVaccineDate>,
    private val fromClass: ClassesEnum,
    private val onItemVaccineClickListener: ItemVaccineClickListener
) :
    RecyclerView.Adapter<AdapterItemVaccineDate.ViewHolderVaccineDate>() {

    interface ItemVaccineClickListener {
        fun onItemVaccineShortClickListener(modelVaccineDate: ModelVaccineDate)

        fun onItemVaccineLocationLister(latLng: LatLng)

        fun onItemVaccineCancelDate()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVaccineDate =
        ViewHolderVaccineDate(
            ItemVaccineDateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


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
            b.tvAddress.text = modelVaccineDate.address
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

                }
            }

            when (fromClass) {
                ClassesEnum.Home -> {
                    if (modelVaccineDate.status == VaccineDateStatus.ATTENDED || modelVaccineDate.status == VaccineDateStatus.CANCELLED)
                        b.statusAttendedOrCanceled.visibility = View.GONE

                    b.tvLotNumber.hide()
                }
                ClassesEnum.MyDates -> {
                    if (modelVaccineDate.status == VaccineDateStatus.ATTENDED)
                        b.btnCancelDate.hide()

                    b.tvLotNumber.hide()
                }
                ClassesEnum.MyVaccines -> {
                    b.tvLotNumber.show()
                    b.btnCancelDate.hide()
                }
            }

            modelVaccineDate.location?.let { latLng ->
                b.tvAddress.setOnClickListener {
                    onItemVaccineClickListener.onItemVaccineLocationLister(
                        latLng
                    )
                }
            }

            b.tvCancelDate.setOnClickListener {
                onItemVaccineClickListener.onItemVaccineCancelDate()
            }

            b.cvItemVaccine.setOnClickListener {
                onItemVaccineClickListener.onItemVaccineShortClickListener(modelVaccineDate)
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