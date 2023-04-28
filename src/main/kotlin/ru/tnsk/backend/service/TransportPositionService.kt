package ru.tnsk.backend.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.tnsk.backend.data.repository.TransportMarkerRepository
import kotlin.time.Duration.Companion.minutes

class TransportPositionService(
    private val transportMarkerRepository: TransportMarkerRepository
) {
    var runing: Boolean = false

    suspend fun start() {
        runing = true
        withContext(Dispatchers.IO) {
            while (runing) {
                try {
                    val markers = transportMarkerRepository.getAllMarkers(true)
                    println("TransportPositionService: ${markers.size}")
                    // todo sleep at night
                    delay(1.minutes)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun stop() {
        runing = false
    }
}