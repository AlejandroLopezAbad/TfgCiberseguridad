package com.alex.tfgciberseguridad.controller

import com.alex.tfgciberseguridad.config.APIConfig
import com.alex.tfgciberseguridad.config.secutiry.jwt.JwtTokenUtil
import com.alex.tfgciberseguridad.dto.*
import com.alex.tfgciberseguridad.exception.UsersBadRequestException
import com.alex.tfgciberseguridad.mapper.toDto
import com.alex.tfgciberseguridad.mapper.toModel
import com.alex.tfgciberseguridad.models.Users
import com.alex.tfgciberseguridad.services.BankAccountService
import com.alex.tfgciberseguridad.services.UsersService
import com.alex.tfgciberseguridad.validators.validate
import jakarta.validation.Valid
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping(APIConfig.API_PATH + "/users")
@CrossOrigin(origins = ["http://localhost:4200"])
class UsersController
@Autowired constructor(
    private val usersService: UsersService,
    private val bankservice: BankAccountService,
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenUtil: JwtTokenUtil,

    ) {


    @PostMapping("/login")
    suspend fun login(@Valid @RequestBody logingDto: UsersLoginDto): ResponseEntity<UsersWithTokenDto> {

        logger.info { "Login:${logingDto.email}" }
        logger.info { "Password:${logingDto.password}" }

        val block = usersService.checkStatus(logingDto.email)
        if (block) {
            throw UsersBadRequestException("Su cuenta ha sido bloqueada.")
        }


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

        usersService.resetContador(true, logingDto.email)

        return ResponseEntity.ok(userWithToken)
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/list")
    suspend fun list(@AuthenticationPrincipal user: Users): ResponseEntity<List<UsersDto>> {

        logger.info { "Obteniendo lista de usuarios" }

        val res = usersService.findAll()
        logger.info { " lista de usuarios" }


        val list = res.map { user ->
            val userDto = user.toDto()
            userDto.numCuenta = bankservice.findCuentaAsociada(user.id!!).toList()
            userDto
        }

        print(res)
        return ResponseEntity.ok(list.toList())
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): ResponseEntity<UsersDto> {
        val res = usersService.loadUserById(id)
        val usersDto = res?.toDto()
        if (res == null) {
            return ResponseEntity.notFound().build()
        } else {
            val cuentas = bankservice.findCuentaAsociada(id).toList()

            usersDto!!.numCuenta = cuentas

        }
        return ResponseEntity.ok(usersDto)
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/me")
    suspend fun findme(@AuthenticationPrincipal user: Users): ResponseEntity<UsersDto> {
        val id = user.id!!
        val res = usersService.loadUserById(id)
        val usersDto = user.toDto()
        if (res == null) {
            return ResponseEntity.notFound().build()
        } else {
            val cuentas = bankservice.findCuentaAsociada(id).toList()
            usersDto.numCuenta = cuentas
        }
        return ResponseEntity.ok(usersDto)
    }

    /**
     * Update me
     *
     * @param user
     * @param usersDto
     * @return
     */

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/me")
    suspend fun updateMe(
        @AuthenticationPrincipal
        user: Users,
        @Valid @RequestBody usersDto: UsersUpdateDto
    ): ResponseEntity<UsersDto> {
        logger.info { "Actualizando usuario: ${user.name}" }

        usersDto.validate()

        val userUpdated = user.copy(

            email = usersDto.email,
            name = usersDto.name,
            telephone = usersDto.telephone.toInt()
        )


        try {
            val userUpdated = usersService.update(userUpdated)

            return ResponseEntity.ok(userUpdated.toDto())
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }

    /**
     * Register
     *
     * @param usersCreateDto
     * @return
     */
    @PostMapping("/register")
    suspend fun register(@RequestBody usersCreateDto: UsersCreateDto): ResponseEntity<UsersWithTokenDto> {
        logger.info { "Registrando al usuario: ${usersCreateDto.name}" }
        try {
            val user = usersCreateDto.validate().toModel()

            val userSaved = usersService.save(user)

            val jwtToken: String = jwtTokenUtil.generateToken(userSaved)
            logger.info { "Token del usuario : ${jwtToken} " }

            return ResponseEntity.ok(UsersWithTokenDto(userSaved.toDto(), jwtToken))

        } catch (e: UsersBadRequestException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        }
    }


}