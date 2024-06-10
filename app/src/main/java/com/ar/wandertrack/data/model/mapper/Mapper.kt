package com.ar.wandertrack.data.model.mapper

interface Mapper<E, D> {
    fun fromEntity(entity: E): D
    fun toEntity(domain: D): E
}