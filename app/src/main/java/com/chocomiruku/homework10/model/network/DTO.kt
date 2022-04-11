package com.chocomiruku.homework10.model.network

import com.chocomiruku.homework10.cropFacts
import com.chocomiruku.homework10.domain.Fish
import com.chocomiruku.homework10.getPath
import com.chocomiruku.homework10.removeTags
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

data class FishJson(
    @Json(name = "Location") val location: String?,
    @Json(name = "Scientific Name") val scientificName: String?,
    @Json(name = "Species Illustration Photo") val speciesIllustrationPhoto: ImageJson?,
    @Json(name = "Species Name") val speciesName: String?,
    @Json(name = "Availability") val availability: String?,
    @Json(name = "Biology") val biology: String?,
    @Json(name = "Health Benefits") val healthBenefits: String?,
    @Json(name = "Path") val path: String
)

@JsonClass(generateAdapter = true)
data class ImageJson(
    val src: String?,
    val alt: String?,
    val title: String?
)

/* Реализовала модицификаю полей в виде единого кастомного адаптера, который, помимо модификации
этих самых полей, сразу конвертирует Json-объект в Domain-объект */
class FishJsonAdapter {
    @FromJson
    fun fishFromJson(fishJson: FishJson): Fish {
        return Fish(
            speciesName = fishJson.speciesName ?: "No information.",
            scientificName = fishJson.scientificName ?: "No information.",
            biology = fishJson.biology?.cropFacts(3, fishJson.path) ?: "No information.",
            location = fishJson.location?.removeTags() ?: "No information.",
            availability = fishJson.availability?.removeTags() ?: "No information.",
            healthBenefits = fishJson.healthBenefits?.removeTags() ?: "No information.",
            illustrationSrc = fishJson.speciesIllustrationPhoto?.src
        )
    }

    /* Метод не совсем корректен, т.к. на выходе получаем Json-объект, который будет не
    полностью эквивалентнен изначальному Json-объекту (потому что Domain-объект хранит не всю
    распаршенную информацию соответственно). В проде так, наверное, лучше не делать, но в
    данном случае решила оставить - сложновато было придумать другие модификации. Да и
    конвертация обратно в Json по заданию не предполагается. */
    @ToJson
    fun fishToJson(fish: Fish): FishJson {
        return FishJson(
            location = fish.location,
            scientificName = fish.scientificName,
            speciesIllustrationPhoto = ImageJson(fish.illustrationSrc, null, null),
            speciesName = fish.speciesName,
            availability = fish.availability,
            biology = fish.biology,
            healthBenefits = fish.healthBenefits,
            path = fish.getPath()
        )
    }
}