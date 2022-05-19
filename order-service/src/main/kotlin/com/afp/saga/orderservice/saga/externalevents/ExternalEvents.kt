package com.afp.saga.orderservice.saga.externalevents

import java.math.BigDecimal
import java.util.*

data class PaymentProcessedEvent(
    val paymentId: UUID,
    val orderId: UUID,
    val paymentStatus: String,
    val paymentValue: BigDecimal
)