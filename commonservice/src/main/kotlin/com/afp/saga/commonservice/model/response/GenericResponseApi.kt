package com.afp.saga.commonservice.model.response

data class ErrorResponse(
    val message : String,
    val statusCode: Int
)

data class CommandApiResponse(
    val identifier: String,
    val message: String
)