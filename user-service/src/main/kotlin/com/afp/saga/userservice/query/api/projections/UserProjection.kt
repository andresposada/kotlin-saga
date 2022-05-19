package com.afp.saga.userservice.query.api.projections

import com.afp.saga.userservice.common.domain.UserRepository
import com.afp.saga.userservice.common.model.User
import com.afp.saga.userservice.query.api.queries.GetUserPaymentDetailsQuery
import org.axonframework.queryhandling.QueryHandler
import org.hibernate.ObjectNotFoundException
import org.springframework.stereotype.Component

@Component
class UserProjection(
    private val userRepository: UserRepository
) {
    @QueryHandler
    fun getUserPaymentDetails(getUserPaymentDetailsQuery: GetUserPaymentDetailsQuery): User =
        userRepository.findById(getUserPaymentDetailsQuery.userId).map { it.toDomain() }.orElseThrow {
            throw ObjectNotFoundException(getUserPaymentDetailsQuery.userId, "User")
        }

}