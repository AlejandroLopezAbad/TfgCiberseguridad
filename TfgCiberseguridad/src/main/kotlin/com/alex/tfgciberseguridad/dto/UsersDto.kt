package com.alex.tfgciberseguridad.dto

/**
 * Dto de usuario, añade usuarios a la API.
 */
data class UsersDto(
    val email:String,
    val username:String,
    val telephone:String,
    val accountNumber:String,
    val dni:String
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

