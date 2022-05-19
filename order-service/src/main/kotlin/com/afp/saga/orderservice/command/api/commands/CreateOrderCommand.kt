package com.afp.saga.orderservice.command.api.commands

import com.afp.saga.orderservice.command.api.aggregates.Address
import com.afp.saga.orderservice.command.api.aggregates.OrderItem
import com.afp.saga.orderservice.common.enums.OrderStatus
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

data class CreateOrderCommand(
    @TargetAggregateIdentifier
    val orderId: UUID,
    val userId : UUID,
    val orderItems : List<OrderItem>,
    val address: Address,
    val orderStatus: OrderStatus,
    val orderValue: BigDecimal
)