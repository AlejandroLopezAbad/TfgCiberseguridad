package com.alex.tfgciberseguridad.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name="bankAccount")
data class BankAccount(
    @Id
    @Column("numCuenta")
    val numCuenta: String,
    @Column("saldo")
    val saldo:Double,
    @Column("prestamos")
    val prestramos:Boolean,
    @Column("user_id")
    val userId:Long
) {
}