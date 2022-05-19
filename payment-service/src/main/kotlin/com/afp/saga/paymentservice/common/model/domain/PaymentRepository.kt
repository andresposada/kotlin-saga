package com.afp.saga.paymentservice.common.model.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PaymentRepository: JpaRepository<PaymentEntity, UUID>