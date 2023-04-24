package ru.tnsk.backend.data.db.mongo.entity

data class City(
    val name: String,
    val type: Int,
    val oid: Int,
    val valid: Boolean
)
