package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.dto.BankAccountDto
import com.alex.tfgciberseguridad.mapper.toDto
import com.alex.tfgciberseguridad.models.BankAccount
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.BankAccountService
import com.alex.tfgciberseguridad.services.UsersService
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH+"/bankAccount")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
class BankAccountController @Autowired constructor(
    private val bankService: BankAccountService,
    private val userService:UsersService
) {

    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<BankAccountDto>> {

        logger.info { "Obteniendo lista de cuentas de banco" }
        val res = bankService.findAll()


        val list = res.map { bankAccount ->
            val bankDto = bankAccount.toDto()
            val user=userService.loadUserById(bankAccount.userId)
            bankDto.userName = user!!.name
            bankDto
        }


        return ResponseEntity.ok(list.toList())
    }
    @GetMapping("/cuentasociada/{id}")
    suspend fun cuentaAsociada(@PathVariable id:Long):ResponseEntity<List<BankAccount>>{

        val res =bankService.findCuentaAsociada(id).toList();

        return ResponseEntity.ok(res)

    }


}