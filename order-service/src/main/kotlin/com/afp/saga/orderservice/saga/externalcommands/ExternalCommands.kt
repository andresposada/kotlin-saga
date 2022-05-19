package com.afp.saga.orderservice.saga.externalcommands

import com.afp.saga.orderservice.saga.externalmodel.UserCardDetails
import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.math.BigDecimal
import java.util.*

data class ValidatePaymentCommand(
    @TargetAggregateIdentifier
    val paymentId: UUID,
    val orderId: UUID,
    val paymentValue: BigDecimal,
    val cardDetails: UserCardDetails
)