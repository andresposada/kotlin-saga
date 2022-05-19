package com.afp.saga.orderservice.common.domain

import com.afp.saga.commonservice.model.domain.GeneralEntity
import com.afp.saga.orderservice.common.enums.OrderStatus
import com.afp.saga.orderservice.command.api.model.events.OrderCreatedEvent
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity : GeneralEntity() {
    @Id
    override lateinit var id: UUID
    @Column(name = "user_id", nullable = false)
    lateinit var userId: UUID
    @OneToMany(mappedBy="order", cascade = [CascadeType.ALL])
    lateinit var orderItems: List<OrderItemEntity>
    @Column(name = "address", nullable = false)
    lateinit var address: String
    @Column(name = "line1", nullable = true)
    var  line1: String? = null
    @Column(name = "line2", nullable = true)
    var  line2: String? = null
    @Column(name = "city", nullable = false)
    lateinit var  city: String
    @Column(name = "state", nullable = false)
    lateinit var state: String
    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var orderStatus: OrderStatus

    companion object {
        fun createOrder(orderCreatedEvent: OrderCreatedEvent): OrderEntity {
            val order = OrderEntity()
            with(orderCreatedEvent) {
                order.id = this.orderId
                order.userId = this.userId
                order. address = this.address.address
                order.line1 = this.address.line1
                order.line2 = this.address.line2
                order.city = this.address.city
                order.state = this.address.state
                order.orderStatus = this.orderStatus
                order.orderItems = this.orderItems.map {
                    val item = OrderItemEntity()
                    item.order = order
                    item.productId = it.productId
                    item.quantity = it.quantity
                    item
                }
            }
            return order
        }
    }

}