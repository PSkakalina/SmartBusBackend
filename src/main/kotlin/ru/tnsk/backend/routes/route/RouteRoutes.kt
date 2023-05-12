package ru.tnsk.backend.routes.route

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.get
import ru.tnsk.backend.data.repository.RouteRepository
import ru.tnsk.backend.domain.model.transport.TransportType
import ru.tnsk.backend.domain.usecase.SearchQueryProcessor
import ru.tnsk.backend.routes.route.model.AllRoutesResponse
import ru.tnsk.backend.routes.utils.badRequest

fun Routing.routeRoutes(
    routeRepository: RouteRepository = get(),
    searchQueryProcessor: SearchQueryProcessor = get()
) {
    val route = "routes"

    suspend fun PipelineContext<Unit, ApplicationCall>.handeRouteSearch(type: String?, route: String?) {
        route ?: run {
            call.respond(HttpStatusCode.BadRequest, "route must be provided when searching by route")
            return
        }

        val intType = type?.toIntOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest, "type (transportType) must be provided when searching by route")
            return
        }

        val transportType = TransportType.values().find { it.value == intType } ?: run {
            call.respond(HttpStatusCode.BadRequest, "type must be one of [0, 1, 2, 7]")
            return
        }

        routeRepository.findRoute(route, transportType)?.let {
            call.respond(it)
        } ?: call.respond(HttpStatusCode.NotFound)
    }

    get(route) {
        val type = call.request.queryParameters["type"]
        val qRoute = call.request.queryParameters["route"]
        if (type != null || qRoute != null) {
            handeRouteSearch(type, qRoute)
        }
        call.respond(AllRoutesResponse(routeRepository.getRoutes()))
    }

    get("$route/{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: run {
            call.respond(HttpStatusCode.BadRequest, "id must be an integer")
            return@get
        }

        routeRepository.findFullRoute(id)?.let {
            call.respond(it)
        } ?: call.respond(HttpStatusCode.NotFound)
    }

    get("$route/search") {
        val query = call.request.queryParameters["query"] ?: return@get badRequest("No query found")
        val type = call.request.queryParameters["type"]?.toIntOrNull()

        val routes = searchQueryProcessor.process(query, type)

        if (routes.isEmpty()) call.respond(HttpStatusCode.NotFound) else call.respond(routes)
    }
}