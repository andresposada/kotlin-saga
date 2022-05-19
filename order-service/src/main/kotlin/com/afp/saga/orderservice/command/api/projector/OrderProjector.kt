package com.afp.saga.orderservice.command.api.projector

import com.afp.saga.orderservice.common.domain.OrderEntity
import com.afp.saga.orderservice.common.domain.OrderRepository
import com.afp.saga.orderservice.command.api.model.events.OrderCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class OrderProjector(
    private val orderRepository: OrderRepository
) {

    @EventHandler
    fun on(orderCreatedEvent: OrderCreatedEvent) {
        val orderEntity = OrderEntity.createOrder(orderCreatedEvent)
        orderRepository.save(orderEntity)
    }

}