package ru.tnsk.backend.data.db.mongo.entity

class TStop(
    val name: String,
    val g_state: Int,
    val g_geoy: Double,
    val g_geox: Double,
    val a_timeInMS: Long,
    val g_parentName: String,
    val g_parentOid: Int,
    val g_oid: Int,
    val g_valid: Boolean,
    val oid: Int,
    val valid: Boolean,
)