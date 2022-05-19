package com.afp.saga.paymentservice.command.api.commands

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.UUID

data class ValidatePaymentCommand(
    @TargetAggregateIdentifier
    val paymentId: UUID,
    val orderId: UUID,
    val paymentValue: BigDecimal,
    val cardDetails: CardDetails
)

data class CardDetails(
    val name: String,
    val cardNumber: String,
    val validUntilMonth: Int,
    val validUntilYear: Int,
    val ccv: String
)
