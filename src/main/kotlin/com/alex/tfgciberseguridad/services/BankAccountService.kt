package com.alex.tfgciberseguridad.services

import com.alex.tfgciberseguridad.dto.BankAccountDto
import com.alex.tfgciberseguridad.mapper.toDto
import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.repositories.BankAccountRepository
import com.alex.tfgciberseguridad.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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






}


/*
//private val passwordEndocer:PassWordEncoder
){


    /**
     * Buscar a un usuario por su id.
     * @param userId id del usuario a buscar
     * @return el usuario encontrado
     */
    suspend fun loadUserById(userId: Long) = withContext(Dispatchers.IO) {
        return@withContext repository.findById(userId)
    }

    /**
     * Guardar un usuario.
     * @param user usuario a salvar.
     * @return el usuario guardado.
     */

    suspend fun save(user: Users): Users = withContext(Dispatchers.IO){
        logger.info { "Guardando usuario: $user" }
        //TODO


        try{
            return@withContext repository.save(user)
        }catch (e:Exception){
            throw Exception("no guardado")
        }

    }


    /**
     * Actualizar usuario
     * @param user usuario a actualizar
     * @return el usuario actualizado.
     */
    suspend fun update(user: Users) = withContext(Dispatchers.IO) {
        logger.info { "Actualizando usuario: $user" }
        //TODO COMPROBAR QUE EL USUARIO EXISTE
        /*        var userDB = repository.findByUsername(user.username)
                    .firstOrNull()

                val updateUser= use*/

        try {
            return@withContext repository.save(user)
        } catch (e: Exception) {
            println(e.message)
            throw Exception("no actualizado")
        }

    }
}
*/
