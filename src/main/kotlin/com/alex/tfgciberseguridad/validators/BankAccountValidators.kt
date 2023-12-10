package com.alex.tfgciberseguridad.validators

import com.alex.tfgciberseguridad.dto.CreateBankAccountDto
import com.alex.tfgciberseguridad.dto.UpdateBankAccountDto
import com.alex.tfgciberseguridad.dto.UsersCreateDto
import com.alex.tfgciberseguridad.exception.BankAccountBadRequestException
import com.alex.tfgciberseguridad.exception.UsersBadRequestException

fun CreateBankAccountDto.validate(): CreateBankAccountDto {
    if (this.numCuenta.isBlank()) {
        throw BankAccountBadRequestException("El numero de cuenta no puede estar vacío")
    } else if (this.saldo<0)
        throw BankAccountBadRequestException("Tiene que tener mas saldo")
    return this
}



fun UpdateBankAccountDto.validate(): UpdateBankAccountDto {
    if (this.numCuenta.isBlank()) {
        throw BankAccountBadRequestException("El numero de cuenta no puede estar vacío")
    } else if (this.saldo < 0) {
        throw BankAccountBadRequestException("Tiene que tener mas saldo")
    }
    return this
}

