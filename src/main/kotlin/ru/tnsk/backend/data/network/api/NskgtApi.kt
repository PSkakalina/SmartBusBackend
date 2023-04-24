package ru.tnsk.backend.data.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.tnsk.backend.data.network.models.MarkersResponse
import ru.tnsk.backend.data.network.models.RouteTypeDto
import ru.tnsk.backend.data.network.models.TracesResponse

class NskgtApi(private val client: HttpClient) {
    suspend fun getMarkers(query: String): MarkersResponse {
        return sendRequest("markers.php", query)
    }

    suspend fun getAllRoutes(): List<RouteTypeDto> {
        return sendRequest("listmarsh.php", "true") // any query will work except empty
    }

    suspend fun getTraces(query: String): TracesResponse {
        return sendRequest("trasses.php", query) // any query will work except empty
    }

    private suspend inline fun <reified T> sendRequest(path: String, query: String): T {
        return client.get("$BASE_URL/$path") {
            url {
                encodedParameters.append("r", query)
            }
        }.body()
    }

    companion object {
        private const val BASE_URL = "https://maps.nskgortrans.ru"
    }
}