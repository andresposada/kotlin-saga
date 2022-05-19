package com.afp.saga.paymentservice.common.model.domain

import com.afp.saga.commonservice.model.domain.GeneralEntity
import com.afp.saga.paymentservice.common.enums.PaymentStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "payments")
class PaymentEntity: GeneralEntity() {
    @Id
    override lateinit var id: UUID
    @Column(name = "order_id", nullable = false)
    lateinit var orderId: UUID
    @Column(name = "payment_date", nullable = false)
    lateinit var paymentDate: LocalDateTime
    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var paymentStatus: PaymentStatus
    @Column(name = "payment_value", nullable = false)
    lateinit var paymentValue: BigDecimal

}