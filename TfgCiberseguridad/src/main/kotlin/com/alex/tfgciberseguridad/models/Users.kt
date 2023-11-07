package com.alex.tfgciberseguridad.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table


/**
 * Users model
 */

@Table(name = "users")

data class Users(
    @Id
    val id :Long?=null,
    @Column("email")
    val email:String,
    @Column("name")
    val username:String,
    @get:JvmName("userPassword")
    @Column("password")
    val password:String,
    @Column("telephone")
    val telephone:Int = 787744741,
    @Column("dni")
    val dni:String,

    @Column("numCuenta")
    val numCuenta:List<BankAccount>


   /* @Column("rol")
    val rol :String=TypeRol.USER.name,*/

) {
/*
    enum class TypeRol() {
        USER,ADMIN,SUPERADMIN
    }*/
}

/**
 * /**
 * Función que verifica si un valor posee una estructura valida de cuenta IBAN.
 * @param {String} strValue String que se desea revisar.
 * @returns {boolean} Indicando si cumple o no las restricciones
*/
function isValidStructureIBAN(strValue){
return /[a-zA-Z]{2}[0-9]{20}$/g.test(strValue);
}// Fin de la función isValidStructureIBAN.

 regex dni ^[0-9]{8,8}[A-Za-z]$
 */
