package com.fuentescreations.vacunatepbarecreation.models

import com.fuentescreations.vacunatepbarecreation.utils.DosesNumber
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus
import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class ModelVaccineDate(
    var status : VaccineDateStatus = VaccineDateStatus.NOT_AVAILABLE,
    var dosesNumber: DosesNumber = DosesNumber.NOT_AVAILABLE,
    var date: Long? = null,
    var hospitalName : String = "",
    var address : String ="",
    var location : LatLng?=null
) : Serializable

fun ModelVaccineDate.getExample(): ModelVaccineDate {
    this.status = listOf(VaccineDateStatus.ATTENDED,VaccineDateStatus.CANCELLED,VaccineDateStatus.PENDING).random()
    this.dosesNumber = listOf(DosesNumber.FIRST,DosesNumber.SECOND,DosesNumber.SECOND,DosesNumber.THIRD).random()
    this.date = System.currentTimeMillis()
    this.hospitalName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    this.address = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    this.location = LatLng(-34.4801636,-58.6344017)

    return this
}