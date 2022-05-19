package com.afp.saga.orderservice.saga

import com.afp.saga.orderservice.command.api.model.events.OrderCreatedEvent
import com.afp.saga.orderservice.saga.externalcommands.ValidatePaymentCommand
import com.afp.saga.orderservice.saga.externalevents.PaymentProcessedEvent
import com.afp.saga.orderservice.saga.externalmodel.User
import com.afp.saga.orderservice.saga.externalqueries.GetUserPaymentDetailsQuery
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.modelling.saga.SagaEventHandler
import org.axonframework.modelling.saga.StartSaga
import org.axonframework.queryhandling.QueryGateway
import org.axonframework.spring.stereotype.Saga
import java.util.*

@Saga
class OrderProcessingSaga(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {
    private val logger = KotlinLogging.logger {}

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    fun handle(orderCreatedEvent: OrderCreatedEvent) {
        logger.info { "OrderCreatedEvent in saga for orderId : ${orderCreatedEvent.orderId}" }
        queryGateway.query(
            GetUserPaymentDetailsQuery(userId = orderCreatedEvent.userId),
            ResponseTypes.instanceOf(User::class.java)
        ).whenCompleteAsync { user, throwable ->
            if (throwable != null) {
                compensateTransaction()
            } else {
                commandGateway.sendAndWait<String>(ValidatePaymentCommand(
                    paymentId = UUID.randomUUID(),
                    orderId = orderCreatedEvent.orderId,
                    paymentValue = orderCreatedEvent.orderValue,
                    cardDetails = user.cardDetails
                ))
            }
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    fun on(paymentProcessedEvent: PaymentProcessedEvent) {
        logger.info { "PaymentProcessedEvent in Saga for orderId: ${paymentProcessedEvent.orderId}" }
    }


    private fun compensateTransaction() {
        logger.error { "Compensating order transaction" }
    }
}