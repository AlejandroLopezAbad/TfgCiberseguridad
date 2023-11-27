package com.alex.tfgciberseguridad.dto


data class BankAccountDto(
    val numCuenta:String,
    val saldo:Double,
    val prestramos:Boolean,
    var userName:String?
){}


data class CreateBankAccountDto(
    val numCuenta:String,
    val saldo:Double,
    val prestamos:Boolean,
    val userId:Long
){}

data class UpdateBankAccountDto(
    val numCuenta: String,
    val saldo: Double,
    val prestamos:Boolean
){


}


