package ru.tnsk.backend.data.core

class IdCache<ID, DATA> {
    private val dataMap = mutableMapOf<ID, DATA>()

    fun executeWithCach(
        forceUpdate: Boolean,
        idExtractor: IdExtractor<ID, DATA>,
        id: ID? = null,
        block: () -> DATA
    ): DATA {
        return if (!forceUpdate && dataMap.containsKey(id)) {
            dataMap[id] ?: error("Inconsistent cache")
        } else block().also {
            dataMap[idExtractor.extract(it)] = it
        }
    }

    fun interface IdExtractor<ID, DATA> {
        fun extract(data: DATA): ID
    }
}