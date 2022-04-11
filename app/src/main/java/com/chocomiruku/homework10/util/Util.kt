package com.chocomiruku.homework10

import com.chocomiruku.homework10.domain.Fish

private val TAGS =
    listOf("<ul>", "<li>", "<p>", "</ul>", "</li>", "</p>", "&nbsp", "<a>", "</a>", "\n", ";")

fun String.removeTags(): String {
    var newText: String = this

    TAGS.map {
        if (newText.contains(it)) {
            newText = newText.replace(it, "")
        }
    }

    newText = newText.replace(".", ". ").replace("<.*>".toRegex(), "")
    return newText
}

fun String.cropFacts(maxFactsCount: Int, path: String): String {
    val sentences = split(".</li>\n")
    val builder = StringBuilder()

    return if (sentences.size > maxFactsCount) {
        for (sentence in sentences.take(maxFactsCount)) {
            builder.append(sentence).append(".</li>")
        }
        builder.append("\n</ul>\n")
            .append("See more on <a href=\"https://www.fishwatch.gov$path\">Fishwatch</a>")
        builder.toString()
    } else replace("\n", "")
}

fun Fish.getPath(): String {
    return "/profiles/" + speciesName.lowercase().replace(" ", "-")
}