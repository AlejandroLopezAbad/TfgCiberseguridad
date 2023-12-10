package com.alex.tfgciberseguridad.config.secutiry

import com.alex.tfgciberseguridad.config.secutiry.jwt.JwtAuthenticationFilter
import com.alex.tfgciberseguridad.config.secutiry.jwt.JwtAuthorizationFilter
import com.alex.tfgciberseguridad.config.secutiry.jwt.JwtTokenUtil
import com.alex.tfgciberseguridad.services.UsersService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

/**
 * Clase que configura la seguridad de Spring y aplica filtros en los END_POINTS
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig
@Autowired constructor(
    private val userService: UsersService,
    private val jwtTokenUtil: JwtTokenUtil
) {
    /**
     * Configuración del Authentication Manager.
     * @param http http security.
     * @return el Authentication Manager ya creado y con el user detail service asignado.
     */
    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder::class.java
        )
        authenticationManagerBuilder.userDetailsService(userService)
        return authenticationManagerBuilder.build()
    }

    /**
     * Configuracion de la cadena de filtros.
     * @param http http security.
     * @return cadena de filtros de la seguridad ya configurada.
     */
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val authenticationManager = authManager(http)

        http
            .csrf()
            .disable()
            .exceptionHandling()
            .and()
            .authenticationManager(authenticationManager)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeHttpRequests()

            .requestMatchers("/public/**").permitAll()

            .requestMatchers("/**").permitAll()

            .requestMatchers("/api/users/login").permitAll()

            .requestMatchers("/api/users/list").hasAnyRole("ADMIN")
            .requestMatchers("/api/bankAccount/list").hasAnyRole("ADMIN")


            .requestMatchers("/api/bankAccount/cuentasociada/{id}").hasAnyRole("ADMIN")
            .requestMatchers("/api/users/{id}").hasAnyRole("ADMIN")


            .requestMatchers("/api/users/me").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/api/bankAccount/cuentasociada").hasAnyRole("USER", "ADMIN")

            .and()

            .addFilter(JwtAuthenticationFilter(jwtTokenUtil, authenticationManager))
            .addFilter(JwtAuthorizationFilter(jwtTokenUtil, userService, authenticationManager))

        return http.build()
    }
}