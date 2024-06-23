package by.audiobooks.mob.data.network

import by.audiobooks.mob.data.network.dto.Data
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.random.Random

class SiteApi {

    companion object {
        private const val HOST = "audiobooksbysite.ew.r.appspot.com"
        private const val USER_AGENT = "audiobooks.by mob app"
        private const val CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789"
        val DESERIALIZATION_SETTINGS = Json {
            // https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/json.md#coercing-input-values
            coerceInputValues = true
            ignoreUnknownKeys = true
        }
    }

    private val httpClient = HttpClient {
        install(UserAgent) {
            agent = USER_AGENT
        }
        install(ContentNegotiation) {
            json(DESERIALIZATION_SETTINGS)
        }
        install(ContentEncoding) {
            gzip()
        }
        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 5)
            exponentialDelay()
        }
    }.also {
        it.sendPipeline.intercept(HttpSendPipeline.State) {
            context.headers.remove(HttpHeaders.Host)
            context.headers.append(HttpHeaders.Host, HOST)
        }
    }

    suspend fun downloadData(): Data =
        withContext(Dispatchers.IO) {
            httpClient.get(getRandomAddress()).body()
        }

    private fun getRandomAddress(): String {
        val randomDomain = (1..Random.nextInt(1,45))
            .map { Random.nextInt(0, CHAR_POOL.length) }
            .map { CHAR_POOL[it] }.joinToString(separator = "")
        return "https://${randomDomain}.appspot.com/data.json"
    }
}
