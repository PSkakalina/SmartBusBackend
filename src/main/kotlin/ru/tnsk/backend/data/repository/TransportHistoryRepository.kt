package ru.tnsk.backend.data.repository

import com.github.benmanes.caffeine.cache.Caffeine
import ru.tnsk.backend.data.db.psql.storage.TransportHistoryStorage
import ru.tnsk.backend.domain.model.transport.Transport

class TransportHistoryRepository(
    private val transportHistoryStorage: TransportHistoryStorage
) {

    private val get = Caffeine.newBuilder()
        .build<Int, List<Transport>> { transportHistoryStorage.getAll(it) }

    private val find = Caffeine.newBuilder()
        .build<Int, List<Transport>> { transportHistoryStorage.find(it) }

    fun all(limit: Int): List<Transport> = get.get(limit)

    fun find(routeId: Int): List<Transport> = find.get(routeId)
}