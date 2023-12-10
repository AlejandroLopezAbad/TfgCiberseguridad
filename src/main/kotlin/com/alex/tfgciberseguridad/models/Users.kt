package com.alex.tfgciberseguridad.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


/**
 * Users model
 */

@Table(name = "users")
data class Users(
    @Id
    val id :Long?=null,
    @Column("email")
    val email:String,
    @Column("name")
    val name:String,
    @get:JvmName("userPassword")
    @Column("password")
    val password:String,
    @Column("telephone")
    val telephone:Int = 787744741,
    @Column("dni")
    val dni:String,
    @Column("rol")
    val rol :String=TypeRol.USER.name,
    @Column("contador")
    var contador :Int,
    @Column("bloqueado")
    var bloqueado:Boolean




) : UserDetails {
    enum class TypeRol() {
        USER,ADMIN,
    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return rol.split(",").map { SimpleGrantedAuthority("ROLE_${it.trim()}") }.toMutableList()
    }


    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email

    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}


