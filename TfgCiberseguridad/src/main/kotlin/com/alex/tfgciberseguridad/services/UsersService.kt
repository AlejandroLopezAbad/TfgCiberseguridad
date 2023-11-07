package com.alex.tfgciberseguridad.services

import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}


@Service
class UsersService
@Autowired constructor(private val repository:UsersRepository
//private val passwordEndocer:PassWordEncoder
){

    /**
     * Buscar a todos los usuarios
     * @return flow con todos los usuarios.
     */
    suspend fun findAll() = withContext(Dispatchers.IO) {
        return@withContext repository.findAll()
    }

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

    suspend fun save(user:Users):Users= withContext(Dispatchers.IO){
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