package by.audiobooks.mob.model.source

import kotlinx.serialization.json.Json

val datasourceDeserializer = Json {
    // https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#coercing-input-values
    coerceInputValues = true
    ignoreUnknownKeys = true
}