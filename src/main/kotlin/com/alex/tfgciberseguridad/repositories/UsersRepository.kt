package com.alex.tfgciberseguridad.repositories

import com.alex.tfgciberseguridad.models.Users
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository:CoroutineCrudRepository<Users,Long> {

    /**
     * Find by email
     *
     * @param email
     * @return
     */
    fun findByEmail(email:String):Flow<Users>







}