package com.afp.saga.orderservice.saga.externalqueries

import java.util.*

data class GetUserPaymentDetailsQuery(
    val userId: UUID
)