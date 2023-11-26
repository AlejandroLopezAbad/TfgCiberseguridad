package com.alex.tfgciberseguridad.mapper

import com.alex.tfgciberseguridad.dto.UsersDto
import com.alex.tfgciberseguridad.models.Users

fun Users.toDto(): UsersDto {

    return UsersDto(
        email = this.email,
        name = this.name,
        telephone = this.telephone.toString(),
        dni =this.dni,
        rol =  this.rol.split(",").map { it.trim() }.toSet(),
        numCuenta = null

    )
    }

