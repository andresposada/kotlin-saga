package com.afp.saga.paymentservice.command.api.projector

import com.afp.saga.paymentservice.command.api.model.events.PaymentProcessedEvent
import com.afp.saga.paymentservice.common.model.domain.PaymentEntity
import com.afp.saga.paymentservice.common.model.domain.PaymentRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PaymentProjector(
    private val paymentRepository: PaymentRepository
) {

    @EventHandler
    fun on(paymentProcessedEvent: PaymentProcessedEvent) {
        val paymentEntity = PaymentEntity()
        with(paymentProcessedEvent) {
            paymentEntity.paymentDate = LocalDateTime.now()
            paymentEntity.paymentStatus = this.paymentStatus
            paymentEntity.paymentValue = this.paymentValue
            paymentEntity.id = this.paymentId
            paymentEntity.orderId = this.orderId
        }
        paymentRepository.save(paymentEntity)
    }

}