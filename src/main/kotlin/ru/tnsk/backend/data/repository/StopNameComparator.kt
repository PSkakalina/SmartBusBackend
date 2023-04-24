package ru.tnsk.backend.data.repository

import io.ktor.util.logging.*
import ru.tnsk.backend.core.utils.LevenshteinDistance
import ru.tnsk.backend.data.network.models.TracesResponse.RouteTracesWrapper.RouteTraces.Trace

class StopNameComparator(compareTo: String) : (Trace) -> Boolean {

    private val reference: String

    private val streetRegex = """ул\.""".toRegex(RegexOption.IGNORE_CASE)
    private val complexRegex = """(ЖК|ж/к)""".toRegex(RegexOption.IGNORE_CASE)
    private val braceRegex = """\(.*\)""".toRegex(RegexOption.IGNORE_CASE)

    private val logger = KtorSimpleLogger("ru.tnsk.backend.data.repository.StopNameComparator")

    init {
        reference = compareTo
            .fixName()
            .replace(" ", "")
            .replace(streetRegex, "")
            .replace(complexRegex, "")
    }

    override fun invoke(p1: Trace): Boolean {
        val name = p1.name ?: return false
        val traceVariant = name
            .replace(" ", "")
            .replace(streetRegex, "")
            .replace(complexRegex, "")

        val isSimilar = traceVariant.contains(reference, true) || reference.contains(traceVariant, true)

        return if (isSimilar) true else {
            val distance = LevenshteinDistance(
                reference.replace(braceRegex, ""),
                traceVariant.replace(braceRegex, ""),
                true
            )
            (distance > 0.7).also {
                if (it) {
                    logger.debug("Levenshtein: $reference == $traceVariant (${distance})")
                }
            }
        }
    }

    private fun String.fixName(): String {
        return this
            .replace("«", "\"")
            .replace("»", "\"")
    }
}
