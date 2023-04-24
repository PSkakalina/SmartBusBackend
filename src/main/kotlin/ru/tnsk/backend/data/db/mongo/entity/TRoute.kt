package ru.tnsk.backend.data.db.mongo.entity

data class TRoute(
    val dataValid: Boolean,
    val tType: Int,
    val routeNumber: String,
    val stopName1: String,
    val stopName2: String,
    val lastStopDiff1: Boolean,
    val lastStopDiff2: Boolean,
    val lastStopIdx: Int,
    val oid: Int,
    val valid: Boolean
)
