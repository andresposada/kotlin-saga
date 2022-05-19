package com.afp.saga.orderservice.common.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderItemRepository : JpaRepository<OrderItemEntity, UUID> {
}