package com.afp.saga.userservice.common.domain

import com.afp.saga.commonservice.model.domain.GeneralEntity
import com.afp.saga.userservice.common.model.UserCardDetails
import java.util.*
import javax.persistence.*
import kotlin.properties.Delegates

@Entity
@Table(name="user_card_details")
class UserCardDetailEntity: GeneralEntity() {
    @Id
    override lateinit var id: UUID
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    lateinit var userEntity: UserEntity
    @Column(name = "first_name", nullable = false)
    lateinit var name: String
    @Column(name = "card_number", nullable = false)
    lateinit var cardNumber: String
    @Column(name = "valid_until_month", nullable = false)
    var validUntilMonth: Int = 0
    @Column(name = "valid_until_year", nullable = false)
    var validUntilYear: Int = 0
    @Column(name = "ccv", nullable = false)
    lateinit var ccv: String
    @Column(name = "default", nullable = false)
    var default: Boolean = false

    fun toDomain(): UserCardDetails =
        UserCardDetails(
            name = this.name,
            cardNumber = this.cardNumber,
            validUntilMonth = this.validUntilMonth,
            validUntilYear = this.validUntilYear,
            ccv = this.ccv
        )
}