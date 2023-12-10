package com.alex.tfgciberseguridad.dto

import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users

/**
 * Dto de usuario, añade usuarios a la API.
 */
data class UsersDto(
    val email:String,
    val name:String,
    val telephone:String,
    val dni:String,
    val rol: Set<String> = setOf(Users.TypeRol.USER.name),
    var numCuenta: List<BankAccount>? = listOf(),
    var contador:Int,
    var bloqueado:Boolean
)

/**
 * dto para el login de usuario.
 */
data class UsersLoginDto(
    val email: String,
    val password: String
)


data class UsersUpdateDto(
    val email:String,
    val name:String,
    val telephone:String,
)

data class UsersWithTokenDto(
    val user: UsersDto,
    val token: String
)

data class UsersCreateDto(
    val email: String,
    val name: String,
    val password: String,
    val telephone: String,
    val dni: String,
    val rol: Set<String> = setOf(Users.TypeRol.USER.name),
)

