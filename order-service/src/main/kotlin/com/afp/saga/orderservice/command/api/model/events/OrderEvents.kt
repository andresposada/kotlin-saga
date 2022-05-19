package com.afp.saga.orderservice.command.api.model.events

import com.afp.saga.orderservice.command.api.aggregates.Address
import com.afp.saga.orderservice.command.api.aggregates.OrderItem
import com.afp.saga.orderservice.common.enums.OrderStatus
import java.math.BigDecimal
import java.util.*

data class OrderCreatedEvent(
    val orderId: UUID,
    val userId : UUID,
    val orderItems : List<OrderItem>,
    val address: Address,
    val orderStatus: OrderStatus,
    val orderValue: BigDecimal
)