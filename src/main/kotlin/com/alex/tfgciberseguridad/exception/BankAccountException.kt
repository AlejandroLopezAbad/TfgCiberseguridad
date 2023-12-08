package com.alex.tfgciberseguridad.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Clase que desarrolla y determina las excepciones de las bankAccounts.
 */
sealed class BankAccountException(message: String) : RuntimeException(message)

/**
 * Excepción específica, código 404 no encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class BankAccountNotFoundException(message: String) : RuntimeException(message)

/**
 * Excepción específica, código 400, error percibido del cliente.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BankAccountBadRequestException(message: String) : RuntimeException(message)