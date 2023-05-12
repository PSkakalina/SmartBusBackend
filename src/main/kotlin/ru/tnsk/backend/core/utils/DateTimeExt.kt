package ru.tnsk.backend.core.utils

import com.soywiz.klock.DateTimeTz
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun DateTimeTz.asLocalDateTime(): LocalDateTime = utc.unixMillisLong.let {
    Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDateTime()
}

val DateTimeTz.zoneOffset: Long
    get() = offset.totalMilliseconds.toLong()

fun LocalDateTime.asDateTimeTz(offset: Long = 7 * 60 * 60 * 1000): DateTimeTz {
    return DateTimeTz.fromUnix(
        atOffset(ZoneOffset.ofHours((offset / (60 * 60 * 1000)).toInt())).toInstant().toEpochMilli()
    )
}