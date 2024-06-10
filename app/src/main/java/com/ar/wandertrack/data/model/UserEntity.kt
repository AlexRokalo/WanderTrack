package com.ar.wandertrack.data.model

import com.ar.wandertrack.data.model.mapper.Mapper
import com.ar.wandertrack.domain.model.User
import com.google.firebase.auth.FirebaseUser

data class UserEntity(
    val uid: String,
    val email: String?,
    val displayName: String?
)

fun FirebaseUser.toUser(): UserEntity {
    return UserEntity(this.uid, this.email, this.displayName)
}

class UserMapper : Mapper<UserEntity, User> {
    override fun fromEntity(entity: UserEntity): User {
        return User(
            id = entity.uid,
            name = entity.displayName ?: "",
            email = entity.email ?: ""
        )
    }

    override fun toEntity(domain: User): UserEntity {
        return UserEntity(
            uid = domain.id,
            email = domain.email,
            displayName = domain.name
        )
    }
}
