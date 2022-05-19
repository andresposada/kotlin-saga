package com.afp.saga.userservice.common.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository: JpaRepository<UserEntity, UUID>