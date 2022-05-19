package com.afp.saga.orderservice.command.api.aggregates

import com.afp.saga.orderservice.command.api.commands.CreateOrderCommand
import com.afp.saga.orderservice.command.api.model.events.OrderCreatedEvent
import com.afp.saga.orderservice.common.enums.OrderStatus
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class OrderAggregate {

    @AggregateIdentifier
    private lateinit var orderId: UUID
    private lateinit var userId: UUID
    private lateinit var orderItems: List<OrderItem>
    private lateinit var address: Address
    private lateinit var orderStatus: OrderStatus

    @CommandHandler
    constructor(createOrderCommand: CreateOrderCommand) {
        // TODO validate command
        with(createOrderCommand) {
            apply(
                OrderCreatedEvent(
                    orderId = this.orderId,
                    userId = this.userId,
                    orderItems = this.orderItems,
                    address = this.address,
                    orderStatus = this.orderStatus,
                    orderValue = this.orderValue
                )
            )
        }
    }

    @EventSourcingHandler
    fun on(orderCreatedEvent: OrderCreatedEvent) {
        this.apply {
          orderId = orderCreatedEvent.orderId
          userId = orderCreatedEvent.userId
          orderItems = orderCreatedEvent.orderItems
          address = orderCreatedEvent.address
          orderStatus = orderCreatedEvent.orderStatus
        }

    }
}

data class OrderItem(
    val productId: UUID,
    val quantity: Int
)

data class Address(
    val address: String,
    val line1: String?,
    val line2: String?,
    val city: String,
    val state: String
)