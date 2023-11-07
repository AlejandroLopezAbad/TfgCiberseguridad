package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.UsersService
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH+"/users")
class UsersController
@Autowired constructor(
    private val usersService:UsersService
){

    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<Users>> {

        logger.info { "Obteniendo lista de usuarios" }

        val res = usersService.findAll()
        logger.info { " lista de usuarios" }

         val list = res.toList()

        print(res)
        return ResponseEntity.ok(list)
    }




}