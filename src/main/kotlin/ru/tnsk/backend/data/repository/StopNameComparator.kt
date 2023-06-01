package ru.tnsk.backend.data.repository

import io.ktor.util.logging.*
import ru.tnsk.backend.core.utils.LevenshteinDistance
import ru.tnsk.backend.data.network.models.TracesResponse.RouteTracesWrapper.RouteTraces.Trace

/**
 * компаратор для сравнения имен остановок
 */
class StopNameComparator(compareTo: String) : (Trace) -> Boolean {

    private val reference: String // строка с которой проводится сравнение

    /* регулярные выражения для отчистки строк */
    // находит вхождения "ул."
    private val streetRegex = """ул\.""".toRegex(RegexOption.IGNORE_CASE)

    // находит вхождения разных написаний обозначения ЖК
    private val complexRegex = """(ЖК|ж/к)""".toRegex(RegexOption.IGNORE_CASE)

    // удаляет текст в скобках (часто встречаются лищние записи вида "(т)" и другие)
    private val braceRegex = """\(.*\)""".toRegex(RegexOption.IGNORE_CASE)

    private val logger = KtorSimpleLogger("ru.tnsk.backend.data.repository.StopNameComparator")

    init {
        reference = compareTo // отчистка строки "эталона"
            .fixName()
            .replace(" ", "")
            .replace(streetRegex, "")
            .replace(complexRegex, "")
    }

    /**
     * Фнукция сравнения
     */
    override fun invoke(p1: Trace): Boolean {
        val name = p1.name ?: return false // если строка равна null сравнение сразу завершается с результатом false
        val traceVariant = name // отчистка строки
            .replace(" ", "")
            .replace(streetRegex, "")
            .replace(complexRegex, "")

        // простое сравнение двух строк
        val isSimilar = traceVariant.contains(reference, true)
                || reference.contains(traceVariant, true)

        return if (isSimilar) true else {
            // Если строки не равны, то высчитывается растояния Левенштейна.
            val distance = LevenshteinDistance(
                reference.replace(braceRegex, ""),
                traceVariant.replace(braceRegex, ""),
                true
            )
            // Если счет сравнения больше 0,7, то строки считаются равными, иначе строки окончательно признаются не равными

            (distance > 0.7).also { // если расстояние
                if (it) {
                    logger.debug("Levenshtein: $reference == $traceVariant (${distance})")
                }
            }
        }
    }

    private fun String.fixName(): String {
        return this
            .replace("«", "\"") // замена ковычек на однообразные
            .replace("»", "\"")
    }
}
