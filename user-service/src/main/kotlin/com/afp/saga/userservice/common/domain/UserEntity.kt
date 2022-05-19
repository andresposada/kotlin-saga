package com.afp.saga.userservice.common.domain

import com.afp.saga.commonservice.model.domain.GeneralEntity
import com.afp.saga.userservice.common.model.User
import com.afp.saga.userservice.common.model.UserCardDetails
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity: GeneralEntity() {

    @Id
    override lateinit var id: UUID
    @Column(name = "first_name", nullable = false)
    lateinit var firstName: String
    @Column(name = "last_name", nullable = false)
    lateinit var lastName: String
    @OneToMany(mappedBy="user", cascade = [CascadeType.ALL])
    lateinit var cardDetails: List<UserCardDetailEntity>

    fun toDomain(): User =
        User(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            cardDetails = this.cardDetails.find { it.default }?.toDomain() ?: this.cardDetails.last().toDomain()
        )

}