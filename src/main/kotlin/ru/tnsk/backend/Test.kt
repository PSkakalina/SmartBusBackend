package ru.tnsk.backend

import com.soywiz.klock.*
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun main() {
    println(LocalDateTime.now())
    println(
        DateTimeTz.fromUnix(
            LocalDateTime.now().atOffset(ZoneOffset.ofHours(7)).toInstant().toEpochMilli()
        )
            .toString(
                DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            )
    )

    val formatter = PatternDateFormat("dd.MM.yyyy HH:mm:ss")

    val d = "11.05.23 17:57:16"
    val b = StringBuilder(d).insert(6, "20")

    val dt = formatter.parse(b.toString())

    val fdt = dt.toOffsetUnadjusted(TimeSpan(7.0 * 60 * 60 * 1000))

    println(formatter.format(fdt))
    println(DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(fdt.local))

    DateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(DateTimeTz.nowLocal()).let(::println)
    DateTimeTz.nowLocal().asLocalDateTime().let(::println)
}

private fun DateTimeTz.asLocalDateTime(): LocalDateTime = utc.unixMillisLong.let {
    Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDateTime()
}