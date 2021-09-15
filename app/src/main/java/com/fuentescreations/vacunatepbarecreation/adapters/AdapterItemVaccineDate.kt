package com.fuentescreations.vacunatepbarecreation.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.fuentescreations.vacunatepbarecreation.R
import com.fuentescreations.vacunatepbarecreation.databinding.ItemVaccineDateBinding
import com.fuentescreations.vacunatepbarecreation.models.ModelVaccineDate
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus

class AdapterItemVaccineDate(private val vaccineDateList: List<ModelVaccineDate>) :
    RecyclerView.Adapter<AdapterItemVaccineDate.ViewHolderVaccineDate>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVaccineDate {
        val b = ItemVaccineDateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = ViewHolderVaccineDate(b)

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolderVaccineDate, position: Int) {
        holder.bind(vaccineDateList[position])
    }

    override fun getItemCount(): Int = vaccineDateList.size

    inner class ViewHolderVaccineDate(private val b: ItemVaccineDateBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(modelVaccineDate: ModelVaccineDate){
            val mContext = itemView.context
            b.tvVaccineDose.text = modelVaccineDate.dosesNumber.name
            b.tvHospitalName.text = modelVaccineDate.hospitalName
            b.tvLocation.text = modelVaccineDate.address

            when (modelVaccineDate.status){
                VaccineDateStatus.ATTENDED -> {
                    b.chipVaccineStatus.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(mContext,R.color.md_blue_100))
                    b.chipVaccineStatus.setTextColor(ContextCompat.getColor(mContext,R.color.md_blue_900))
                    b.chipVaccineStatus.chipIcon?.setTint(ContextCompat.getColor(mContext,R.color.md_blue_100))
                }
                VaccineDateStatus.PENDING -> TODO()
                VaccineDateStatus.CANCELLED -> TODO()
                VaccineDateStatus.NOT_AVAILABLE -> TODO()
            }

        }
    }
}