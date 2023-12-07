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
  //  @PreAuthorize("hasAnyRole('ADMIN')") cambiar security config
    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<BankAccountDto>> {

        logger.info { "Obteniendo lista de cuentas de banco" }
        val res = bankService.findAll()


      val list= bankService.loadUsersToBank(res)


      return ResponseEntity.ok(list)
    }
    //FALLO DE SEGURIDAD PORQUE VES TODOS LOS ID Y ES AUTONUMERICO ; SE PODRIA TBM AÑADIR UN CAMPO UUID
    @GetMapping("/cuentasociada/{id}")
    suspend fun cuentaAsociada(@PathVariable id:Long):ResponseEntity<List<BankAccountDto>>{

        val res =bankService.findCuentaAsociada(id);

        val list= bankService.loadUsersToBank(res)

        return ResponseEntity.ok(list)

    }
  //  @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @GetMapping("/cuentasociada")
//    suspend fun cuentaAsociada(@AuthenticationPrincipal user: Users):ResponseEntity<List<BankAccount>>{
//
//        val res =bankService.findCuentaAsociada(user.id).toList();
//
//        return ResponseEntity.ok(res)
//
//    }




}