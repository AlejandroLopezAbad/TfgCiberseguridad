package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.BankAccountService
import com.alex.tfgciberseguridad.services.UsersService
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH+"/bankAccount")
class BankAccountController @Autowired constructor(
    private val bankService: BankAccountService
) {

    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<BankAccount>> {

        logger.info { "Obteniendo lista de cuentas de banco" }
        val res = bankService.findAll().toList()

        return ResponseEntity.ok(res)
    }
    @GetMapping("/cuentasociada")
    suspend fun cuentaAsociada(id:Long):ResponseEntity<List<BankAccount>>{

        val res =bankService.findCuentaAsociada(id).toList();

        return ResponseEntity.ok(res)

    }


}