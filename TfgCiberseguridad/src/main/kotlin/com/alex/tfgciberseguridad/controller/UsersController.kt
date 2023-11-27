package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.config.secutiry.jwt.JwtTokenUtil
import com.alex.tfgciberseguridad.dto.UsersDto
import com.alex.tfgciberseguridad.dto.UsersLoginDto
import com.alex.tfgciberseguridad.dto.UsersWithTokenDto
import com.alex.tfgciberseguridad.mapper.toDto
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.BankAccountService
import com.alex.tfgciberseguridad.services.UsersService
import jakarta.validation.Valid
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH+"/users")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
class UsersController
@Autowired constructor(
    private val usersService:UsersService,
    private val bankservice:BankAccountService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,

    ){


    @PostMapping("/login")
    fun login(@Valid @RequestBody logingDto: UsersLoginDto): ResponseEntity<UsersWithTokenDto> {

        logger.info { "Login:${logingDto.email}" }
        logger.info { "Password:${logingDto.password}" }

        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                logingDto.email,
                logingDto.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val user = authentication.principal as Users

        val jwtToken: String = jwtTokenUtil.generateToken(user)

        logger.info { "Token: ${jwtToken}" }

        val userWithToken = UsersWithTokenDto(user.toDto(), jwtToken)

        return ResponseEntity.ok(userWithToken)
    }



    @GetMapping("/list")
    suspend fun list(): ResponseEntity<List<UsersDto>> {

        logger.info { "Obteniendo lista de usuarios" }

        val res = usersService.findAll()
        logger.info { " lista de usuarios" }


        val list = res.map { user ->
            val userDto = user.toDto()
            userDto.numCuenta = bankservice.findCuentaAsociada(user.id).toList()
            userDto
        }

        print(res)
        return ResponseEntity.ok(list.toList())
    }

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id:Long): ResponseEntity<UsersDto> {
        val res = usersService.loadUserById(id)
        val usersDto= res?.toDto()
        if(res==null){
            return ResponseEntity.notFound().build()
        }else{
            val cuentas= bankservice.findCuentaAsociada(id).toList();

                usersDto!!.numCuenta=cuentas

        }
        return ResponseEntity.ok(usersDto)
    }








}