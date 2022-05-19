package com.afp.saga.orderservice.command.api.model.request

import java.math.BigDecimal
import java.util.UUID

data class AddOrderRequestApi(
    val userId : UUID,
    val orderItems : List<OrderItemRequestApi>,
    val address: OrderAddressRequestApi,
    val value: BigDecimal
)

data class OrderItemRequestApi(
    val productId: UUID,
    val quantity: Int
)

data class OrderAddressRequestApi(
    val address: String,
    val line1: String?,
    val line2: String?,
    val city: String,
    val state: String
)