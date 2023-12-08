package com.alex.tfgciberseguridad.services

import com.alex.tfgciberseguridad.exception.UsersBadRequestException
import com.alex.tfgciberseguridad.exception.UsersNotFoundException
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}


@Service
class UsersService
@Autowired constructor(private val repository:UsersRepository,
                       private val passwordEncoder: PasswordEncoder
):UserDetailsService {


    override fun loadUserByUsername(email: String): UserDetails = runBlocking {
        return@runBlocking repository.findByEmail(email).firstOrNull()
            ?: throw UsersNotFoundException("Usuario no encontrado con username: $email") //TODO EXCEPCIONES
    }


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

    suspend fun save(user: Users, isAdmin: Boolean = false): Users = withContext(Dispatchers.IO) {
        logger.info { "Guardando usuario: $user" }

        if (repository.findByEmail(user.email) != null) {
            throw UsersBadRequestException("El usuario ya existe con este email")
        }
        if (repository.findByTelephone(user.telephone).firstOrNull() != null) {
            throw UsersBadRequestException("El usuario ya existe con este numero de telefono ")
        }

        var newUser = user.copy(
            password = passwordEncoder.encode(user.password),
            rol = Users.TypeRol.USER.name,
        )
        if (isAdmin) {
            newUser = newUser.copy(
                rol = Users.TypeRol.ADMIN.name
            )
        }

        logger.info(newUser.toString())
        try {
            return@withContext repository.save(newUser)
        } catch (e: Exception) {
            throw UsersBadRequestException("Error al crear el usuario: Nombre de usuario o email ya existen")
        }

    }


    /**
     * Actualizar usuario
     * @param user usuario a actualizar
     * @return el usuario actualizado.
     */
    suspend fun update(user: Users) = withContext(Dispatchers.IO) {
        logger.info { "Actualizando usuario: $user" }

       val userDB = repository.findUsersByEmail(user.email).firstOrNull()

        if (userDB != null && userDB.email != user.email) {
            throw UsersBadRequestException("El email ya existe")
        }

        val updateUser= user.copy()

        try {
            return@withContext repository.save(updateUser)
        } catch (e: Exception) {
            println(e.message)
            throw  UsersBadRequestException("Error al actualizar el usuario: Nombre de usuario o email ya existen")
        }

    }

    /**
     * Buscar a un usuario por su email.
     * @param email id del usuario a buscar
     * @return el usuario encontrado
     */
    suspend fun loadUserByemail(email: String) = withContext(Dispatchers.IO) {
        return@withContext repository.findUsersByEmail(email).firstOrNull()
    }


    suspend fun sumarContador(email: String) = withContext(Dispatchers.IO) {
       val user=loadUserByemail(email)

        if (user != null) {
            user.contador=user.contador+1
            update(user)
        }


    }

    suspend fun checkContador(email:String)= withContext(Dispatchers.IO){
        var cont=0
        var block = true
        val user= loadUserByemail(email)

        if (user != null) {
            cont=user.contador
            if(cont>5 ){
                user.bloqueado = block
                 update(user)
            }
        }
        return@withContext cont
    }

    suspend fun checkBlock(email: String)= withContext(Dispatchers.IO) {
        val block = true
        val u=  loadUserByemail(email)

            return@withContext u?.bloqueado == block

    }


    suspend fun resetContador(reset:Boolean,email:String)= withContext(Dispatchers.IO){
       val u = loadUserByemail(email)

        if (reset){
            if (u != null) {
                u.contador=0
                update(u)
            }
        }
    }

    suspend fun checkStatus(email:String)= withContext(Dispatchers.IO) {
        sumarContador(email)
        checkContador(email)
        return@withContext checkBlock(email)
    }


}
