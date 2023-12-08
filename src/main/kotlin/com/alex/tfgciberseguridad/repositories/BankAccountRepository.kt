package com.alex.tfgciberseguridad.repositories

import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface BankAccountRepository : CoroutineCrudRepository<BankAccount, Long> {

    fun findByNumCuenta(numCuenta:String):Flow<BankAccount>

    fun findAllByUserId(userId:Long): Flow<BankAccount>
}