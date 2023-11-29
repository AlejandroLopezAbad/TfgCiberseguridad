package com.alex.tfgciberseguridad.config.secutiry.jwt

import com.alex.tfgciberseguridad.services.UsersService
import io.netty.handler.codec.http.HttpHeaderNames.AUTHORIZATION
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException

private val logger = KotlinLogging.logger {}

class JwtAuthorizationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val service: UsersService,
    authManager: AuthenticationManager,
) : BasicAuthenticationFilter(authManager) {

    /**
     * Do filter internal
     *
     * @param req
     * @param res
     * @param chain
     */
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = req.getHeader(AUTHORIZATION.toString())
        if (header == null || !header.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }
        getAuthentication(header.substring(7))?.also {
            SecurityContextHolder.getContext().authentication = it
        }
        chain.doFilter(req, res)
    }

    /**
     * Get Authentication
     *
     * @param token
     * @return
     */
    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken? = runBlocking {
        logger.info { "Obteniendo autenticación" }
        if (!jwtTokenUtil.isTokenValid(token)) return@runBlocking null
        val userId = jwtTokenUtil.getUserIdFromJwt(token)
        val user = service.loadUserById(userId.toLong())
        return@runBlocking UsernamePasswordAuthenticationToken(
            user,
            null,
            user?.authorities

        )
    }
}