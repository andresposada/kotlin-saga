package com.afp.saga.paymentservice.command.api.aggregates

import com.afp.saga.paymentservice.command.api.commands.ValidatePaymentCommand
import com.afp.saga.paymentservice.command.api.model.events.PaymentProcessedEvent
import com.afp.saga.paymentservice.common.enums.PaymentStatus
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.AggregateLifecycle.*
import org.axonframework.spring.stereotype.Aggregate
import java.lang.reflect.Constructor
import java.math.BigDecimal
import java.util.*

@Aggregate
class PaymentAggregate {

    @AggregateIdentifier
    private lateinit var paymentId: UUID
    private lateinit var orderId: UUID
    private lateinit var paymentStatus: PaymentStatus
    private lateinit var paymentValue: BigDecimal

    @CommandHandler
    constructor(validatePaymentCommand: ValidatePaymentCommand) {
        // TODO validate payment details (card exists, enough money in the card to pay the order, etc)
        apply(PaymentProcessedEvent(
            paymentId = validatePaymentCommand.paymentId,
            orderId = validatePaymentCommand.orderId,
            paymentStatus = PaymentStatus.COMPLETED.toString(),
            paymentValue = validatePaymentCommand.paymentValue
        ))
    }

    @EventSourcingHandler
    fun on(paymentProcessedEvent: PaymentProcessedEvent) {
        this.paymentId = paymentProcessedEvent.paymentId
        this.orderId = paymentProcessedEvent.orderId
        this.paymentStatus = PaymentStatus.valueOf(paymentProcessedEvent.paymentStatus)
        this.paymentValue = paymentProcessedEvent.paymentValue
    }
}