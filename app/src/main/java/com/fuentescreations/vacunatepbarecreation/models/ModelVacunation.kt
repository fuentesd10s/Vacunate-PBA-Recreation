package com.fuentescreations.vacunatepbarecreation.models

import com.fuentescreations.vacunatepbarecreation.utils.DosesNumber
import com.fuentescreations.vacunatepbarecreation.utils.VaccineDateStatus
import com.google.android.gms.maps.model.LatLng

data class ModelVaccineDate(
    var status : VaccineDateStatus = VaccineDateStatus.NOT_AVAILABLE,
    var dosesNumber: DosesNumber = DosesNumber.NOT_AVAILABLE,
    var date: Long? = null,
    var hospitalName : String = "",
    var address : String ="",
    var location : LatLng?=null
)