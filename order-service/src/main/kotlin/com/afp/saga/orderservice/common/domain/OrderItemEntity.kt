package com.afp.saga.orderservice.common.domain

import com.afp.saga.commonservice.model.domain.GeneralEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "order_items")
class OrderItemEntity: GeneralEntity() {

    @Id
    override lateinit var id: UUID

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    lateinit var order: OrderEntity

    @Column(name = "product_id", nullable = false)
    lateinit var productId: UUID

    @Column(name = "quantity")
    var quantity: Int = 0

}