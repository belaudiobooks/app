package by.audiobooks.mob.network

import by.audiobooks.mob.model.source.Data
import by.audiobooks.mob.model.source.datasourceDeserializer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

class SiteApi {

    companion object {
        const val ADDR = "https://audiobooksbysite.ew.r.appspot.com/data.json"
        const val USER_AGENT = "audiobooks.by mob app"
    }

    private val httpClient = HttpClient {
        install(UserAgent) {
            agent = USER_AGENT
        }
        install(ContentNegotiation) {
            json(datasourceDeserializer)
        }
        install(ContentEncoding) {
            gzip()
        }
    }

    suspend fun downloadData(): Data {
        return httpClient.get(ADDR).body()
    }

}