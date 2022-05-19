package com.afp.saga.orderservice.command.api.controller

import com.afp.saga.commonservice.model.response.CommandApiResponse
import com.afp.saga.orderservice.command.api.aggregates.Address
import com.afp.saga.orderservice.command.api.aggregates.OrderItem
import com.afp.saga.orderservice.command.api.commands.CreateOrderCommand
import com.afp.saga.orderservice.common.enums.OrderStatus
import com.afp.saga.orderservice.command.api.model.request.AddOrderRequestApi
import com.afp.saga.orderservice.command.api.model.request.OrderAddressRequestApi
import com.afp.saga.orderservice.command.api.model.request.OrderItemRequestApi
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/orders")
class OrderCommandController(
    private val commandGateway: CommandGateway
) {

    @PostMapping
    fun createOrder(@RequestBody addOrderRequestApi: AddOrderRequestApi): ResponseEntity<CommandApiResponse> {
        val id = commandGateway.sendAndWait<String>(addOrderRequestApi.toCommand())
        return ResponseEntity.ok(CommandApiResponse(id, "Order has been created!"))
    }

    fun AddOrderRequestApi.toCommand() =
        CreateOrderCommand(
            orderId = UUID.randomUUID(),
            userId = this.userId,
            orderItems = orderItems.map { it.toCommand() },
            address = this.address.toCommand(),
            orderStatus = OrderStatus.CREATED,
            orderValue = this.value
        )

    fun OrderItemRequestApi.toCommand() =
        OrderItem(
            productId = this.productId,
            quantity = this.quantity
        )

    fun OrderAddressRequestApi.toCommand() =
        Address(
            address = this.address,
            line1 = this.line1,
            line2 = this.line2,
            city = this.city,
            state = this.state
        )
}