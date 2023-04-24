package ru.tnsk.backend.core.utils

import kotlin.math.max
import kotlin.math.min

/**
 * Реализация алгоритма Растояния Левенштейна,
 */
object LevenshteinDistance {
    private fun getLevenshteinDistance(first: String, second: String, ignoreCase: Boolean): Int {
        val fLength = first.length
        val sLength = second.length
        val distance = Array(fLength + 1) { IntArray(sLength + 1) }
        for (i in 1..fLength) {
            distance[i][0] = i
        }

        for (j in 1..sLength) {
            distance[0][j] = j
        }

        var cost: Int
        for (i in 1..fLength) {
            for (j in 1..sLength) {
                cost = if (first[i - 1].equals(second[j - 1], ignoreCase)) 0 else 1
                distance[i][j] = min(
                    min(distance[i - 1][j] + 1, distance[i][j - 1] + 1),
                    distance[i - 1][j - 1] + cost
                )
            }
        }

        return distance[fLength][sLength]
    }

    fun findSimilarity(x: String?, y: String?, ignoreCase: Boolean = false): Double {
        require(!(x == null || y == null)) { "Strings should not be null" }

        val maxLength = max(x.length, y.length)
        return if (maxLength > 0) {
            (maxLength * 1.0 - getLevenshteinDistance(x, y, ignoreCase)) / maxLength * 1.0
        } else 1.0
    }

    operator fun invoke(x: String?, y: String?, ignoreCase: Boolean = false): Double = findSimilarity(x, y, ignoreCase)
}

fun main() {
    val brarcetRegex = """\(.*\)""".toRegex(RegexOption.IGNORE_CASE)

    val s1 = "Отделениесвязи№13(Северноекладбище-лето)".replace(brarcetRegex, "")
    val s2 = "Отделениесвязи№13(Пашино)".replace(brarcetRegex, "")

    LevenshteinDistance(s1, s2)
//    val similarity = LevenshteinDistance.findSimilarity("Отделениесвязи№13(Северноекладбище-лето)", "Отделениесвязи№13(Пашино)", true)
    val similarity = LevenshteinDistance(s1, s2)
    println(similarity) // 0.8421052631578947
}