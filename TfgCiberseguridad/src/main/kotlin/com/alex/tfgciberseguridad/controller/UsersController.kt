package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.BankAccountService
import com.alex.tfgciberseguridad.services.UsersService
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH+"/users")
class UsersController
@Autowired constructor(
    private val usersService:UsersService,
    private val bankservice:BankAccountService
){

    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<Users>> {

        logger.info { "Obteniendo lista de usuarios" }

        val res = usersService.findAll()
        logger.info { " lista de usuarios" }

         val list = res.toList().onEach { users ->
             users.numCuenta=   bankservice.findCuentaAsociada(users.id).toList() ;
         }


        print(res)
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id:Long): ResponseEntity<Users> {
        val res = usersService.loadUserById(id)

        val cuentas= bankservice.findCuentaAsociada(id).toList();
        if(res==null){
            return ResponseEntity.notFound().build()
        }else{
            res.numCuenta=cuentas
        }
        return ResponseEntity.ok(res)
    }








}