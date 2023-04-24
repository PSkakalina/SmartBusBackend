package ru.tnsk.backend.data.db.mongo.storage

import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo
import ru.tnsk.backend.data.db.mongo.entity.City
import ru.tnsk.backend.data.db.mongo.entity.TRoute
import ru.tnsk.backend.data.db.mongo.entity.TStop

class TRouteStorage {
    private val client = KMongo.createClient().coroutine
    private val database = client.getDatabase("tnsk4571")
    suspend fun getTRoutes(): List<TRoute> {
        val col = database.getCollection<TRoute>("TRoute")
//TRoute::dataValid eq true,
        return col.find(TRoute::valid eq true).toList()
    }

    suspend fun getCities(): List<City> {
        return database
            .getCollection<City>("TCity")
            .find(City::valid eq true)
            .toList()
    }

    suspend fun getTStops(): List<TStop> {
        return database
            .getCollection<TStop>("TStop")
            .find(TStop::valid eq true)
            .toList()
    }
}