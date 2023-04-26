package ru.tnsk.backend.data.repository

import io.ktor.util.logging.*
import ru.tnsk.backend.data.db.psql.storage.RouteStorage
import ru.tnsk.backend.data.db.psql.storage.StopStorage
import ru.tnsk.backend.data.db.psql.storage.TraceStorage
import ru.tnsk.backend.data.network.models.TracesResponse.RouteTracesWrapper.RouteTraces.Trace
import ru.tnsk.backend.data.network.storage.NskgtStorage

class ImportRouteRepository(
    private val nskgtStorage: NskgtStorage,
    private val routeStorage: RouteStorage,
    private val stopStorage: StopStorage,
    private val traceStorage: TraceStorage
) {
    private val logger = KtorSimpleLogger("ru.tnsk.backend.data.repository.RouteRepository")

    // todo make it to work as real upsert, move it to use case
    suspend fun upsertRoutes() {
        nskgtStorage.getAllRoutes().forEach { routeType ->
            routeType.ways.forEach routeForEach@{ route ->
                val traces: List<Trace> = nskgtStorage
                    .getTraces(routeType.type, route.route)
                    .routes
                    .first()
                    .routeTrace
                    .first()
                    .traces

                val firstStop = traces.find(StopNameComparator(route.stopb)) ?: traces.first()

                val lastStop =
                    traces.find(StopNameComparator(route.stope)) ?: findDuplicateAdjacentTraces(traces) ?: run {
                        logger.error("NO lastStop: $route")
                        return@routeForEach
                    }

                val firstStopSaved = stopStorage.createOrGet(
                    firstStop.id!!,
                    firstStop.name!!,
                    firstStop.lat,
                    firstStop.lng,
                    firstStop.len!!
                )

                val lastStopSaved = stopStorage.createOrGet(
                    lastStop.id!!,
                    lastStop.name!!,
                    lastStop.lat,
                    lastStop.lng,
                    lastStop.len!!
                )

                val savedRoute =
                    routeStorage.create(
                        route.route,
                        route.name,
                        routeType.type,
                        firstStopSaved.id,
                        lastStopSaved.id
                    )

                traces.forEach { trace ->
                    if (trace.name == null) {
                        traceStorage.createOrGet(trace.lat, trace.lng, null, savedRoute.id.value)
                    } else {
                        stopStorage.createOrGet(trace.id!!, trace.name, trace.lat, trace.lng, trace.len!!).let {
                            traceStorage.createOrGet(trace.lat, trace.lng, it.id, savedRoute.id.value)
                        }
                    }
                }
            }
        }
    }

    private fun findDuplicateAdjacentTraces(traces: List<Trace>): Trace? {
        val stops = traces.filter { it.name != null }

        for (i in 1 until stops.size) {
            if (stops[i] == stops[i - 1]) {
                return stops[i]
            }
        }
        return null
    }
}