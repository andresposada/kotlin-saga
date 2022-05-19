package com.afp.saga.userservice.common.model

import java.util.UUID

data class User(
    val id: UUID,
    val firstName: String,
    val lastName: String,
    val cardDetails: UserCardDetails
)

data class UserCardDetails(
    val name: String,
    val cardNumber: String,
    val validUntilMonth: Int,
    val validUntilYear: Int,
    val ccv: String
)
