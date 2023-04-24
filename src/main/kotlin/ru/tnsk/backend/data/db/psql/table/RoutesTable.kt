package ru.tnsk.backend.data.db.psql.table

import org.jetbrains.exposed.dao.id.IntIdTable
import ru.tnsk.backend.domain.model.transport.TransportType

object RoutesTable : IntIdTable("routes") {
    val route = text("marsh")
    val name = text("name")
    val transportType = enumeration<TransportType>("transportType")
    val firstStop = reference("firstStop", StopsTable, fkName = "firstStop")
    val lastStop = reference("lastStop", StopsTable, fkName = "lastStop")
}