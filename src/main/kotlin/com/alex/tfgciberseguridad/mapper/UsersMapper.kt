package com.alex.tfgciberseguridad.mapper

import com.alex.tfgciberseguridad.dto.UsersCreateDto
import com.alex.tfgciberseguridad.dto.UsersDto
import com.alex.tfgciberseguridad.models.Users

fun Users.toDto(): UsersDto {

    return UsersDto(
        email = this.email,
        name = this.name,
        telephone = this.telephone.toString(),
        dni =this.dni,
        rol =  this.rol.split(",").map { it.trim() }.toSet(),
        numCuenta = null,
        contador = contador,
        bloqueado=bloqueado
    )
    }


/**
 * Mapper de UsersCreateDto al modelo de Users.
 */
fun UsersCreateDto.toModel(): Users {
    return Users(
        email = this.email,
        name = this.name,
        password = this.password,
        telephone = this.telephone.toInt(),
        dni=this.dni,
        rol =  this.rol.joinToString(", ") { it.uppercase().trim() },
        contador = 0,
        bloqueado = false
    )
}

