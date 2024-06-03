package com.ar.wandertrack.data.model.mappers

import com.ar.wandertrack.data.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(): User {
    return User(this.uid, this.email, this.displayName)
}