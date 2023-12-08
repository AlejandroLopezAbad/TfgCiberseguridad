package com.alex.tfgciberseguridad.services

import com.alex.tfgciberseguridad.dto.BankAccountDto
import com.alex.tfgciberseguridad.exception.BankAccountBadRequestException
import com.alex.tfgciberseguridad.exception.UsersBadRequestException
import com.alex.tfgciberseguridad.mapper.toDto
import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.repositories.BankAccountRepository
import com.alex.tfgciberseguridad.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}
@Service
class BankAccountService
@Autowired constructor(
    private val bankAccountrepo:BankAccountRepository,
    private val userService:UsersService
){
    /**
     * Buscar a todos los bankAccount
     * @return flow con todos los cuentas de banco.
     */
    suspend fun findAll() = withContext(Dispatchers.IO) {
        return@withContext bankAccountrepo.findAll()
    }


    suspend fun findCuentaAsociada(id:Long)= withContext(Dispatchers.IO){
        return@withContext  bankAccountrepo.findAllByUserId(id)
    }

    suspend fun loadUsersToBank (res: Flow<BankAccount>) = withContext(Dispatchers.IO){
        val list = res.map { bankAccount ->
            val bankDto = bankAccount.toDto()
            val user=userService.loadUserById(bankAccount.userId)
            bankDto.userName = user!!.name
            bankDto
        }
        return@withContext list.toList()
    }



    /**
     * Guardar una BankAccount.
     * @param bankAccount BankAccount a salvar.
     * @return el BankAccount guardado.
     */

    suspend fun save(bankAccount: BankAccount): BankAccount = withContext(Dispatchers.IO) {
        logger.info { "Guardando usuario: $bankAccount" }

        if (bankAccountrepo.findByNumCuenta(bankAccount.numCuenta).firstOrNull() != null) {
            throw BankAccountBadRequestException("Ya existe una cuenta con este numero de cuenta")
        }
        logger.info(bankAccount.toString())
        try {
            return@withContext bankAccountrepo.save(bankAccount)
        } catch (e: Exception) {
            throw UsersBadRequestException("Error al crear el usuario: Nombre de usuario o email ya existen")
        }

    }

    /**
     * Actualizar usuario
     * @param user usuario a actualizar
     * @return el usuario actualizado.
     */
    suspend fun update(bankAccount: BankAccount) = withContext(Dispatchers.IO) {
        logger.info { "Actualizando usuario: $bankAccount" }

        val bankAccountDb = bankAccountrepo.findByNumCuenta(bankAccount.numCuenta)
            .firstOrNull()
        if (bankAccountDb != null && bankAccountDb.numCuenta != bankAccount.numCuenta) {
            throw UsersBadRequestException("El numero de cuenta ya existe")
        }

        try {
            return@withContext bankAccountrepo.save(bankAccount)
        } catch (e: Exception) {
            println(e.message)
            throw  UsersBadRequestException("Error al actualizar la cuenta bancaria : Numero de cuenta ya existen")
        }

    }

    
}


