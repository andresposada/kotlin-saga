package com.afp.saga.paymentservice.command.api.model.events

import com.afp.saga.paymentservice.common.enums.PaymentStatus
import java.math.BigDecimal
import java.util.UUID

data class PaymentProcessedEvent(
    val paymentId: UUID,
    val orderId: UUID,
    val paymentStatus: String,
    val paymentValue: BigDecimal
)