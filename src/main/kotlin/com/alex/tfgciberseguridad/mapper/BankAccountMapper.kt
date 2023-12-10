package com.alex.tfgciberseguridad.mapper

import com.alex.tfgciberseguridad.dto.BankAccountDto
import com.alex.tfgciberseguridad.models.BankAccount


fun BankAccount.toDto():BankAccountDto{
    return BankAccountDto(
        numCuenta = this.numCuenta,
        saldo=this.saldo,
        prestramos=this.prestramos,
        userName = null
    )
}