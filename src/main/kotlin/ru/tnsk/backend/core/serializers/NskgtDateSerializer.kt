package ru.tnsk.backend.core.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat
import java.util.*

object NskgtDateSerializer : KSerializer<Date> {
    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    private val formatter = SimpleDateFormat("dd.M.yy hh:mm:ss")

    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(formatter.format(value))
    override fun deserialize(decoder: Decoder): Date = formatter.parse(decoder.decodeString())
}