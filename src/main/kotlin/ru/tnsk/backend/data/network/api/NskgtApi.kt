package ru.tnsk.backend.data.network.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import ru.tnsk.backend.data.network.models.MarkersResponse
import ru.tnsk.backend.data.network.models.RouteTypeDto
import ru.tnsk.backend.data.network.models.TracesResponse

/**
 * Класс для взаимодействия с API NskGortrans
 *
 * @param client клиант для HTTP запросов
 */
class NskgtApi(private val client: HttpClient) {

    /**
     * Запрос для получения текущей геопозиции бортов для маршрутов
     * @param query маршруты для запроса (не более 5, все маршруты после 5го будут проигнорированы)
     * @return список содержащий информацию о бортах
     */
    suspend fun getMarkers(query: String): MarkersResponse {
        return sendRequest("markers.php", query)
    }

    /**
     * Запрос всех известных маршрутов
     *
     * @return список маршрутов
     */
    suspend fun getAllRoutes(): List<RouteTypeDto> {
        return sendRequest("listmarsh.php", "true") // любое "query" будет работать кроме пустой строки
    }

    /**
     * Запрос для получения точек конкретного маршрута
     * @param query маршруты для запроса
     * @return список точек с остановками
     */
    suspend fun getTraces(query: String): TracesResponse {
        return sendRequest("trasses.php", query)
    }

    /**
     * Обертка для отправки запроса в API NSKGortrans
     */
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