package com.fuentescreations.vacunatepbarecreation.utils

enum class VaccineDateStatus(val value: String) {
    ATTENDED("Atendido"),
    PENDING("Pendiente"),
    CANCELLED("Cancelado"),
    NOT_AVAILABLE("Sin turno")
}

enum class DosesNumber(val value: String) {
    FIRST("Primera dosis"),
    SECOND("Segunda dosis"),
    THIRD("Tercera dosis"),
    NOT_AVAILABLE("")
}

enum class ClassesEnum(){
    Home,
    MyDates,
    MyVaccines
}