package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import by.audiobooks.mob.domain.Publisher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllPublishers(context: CoroutineContext = Dispatchers.IO): Flow<List<Publisher>> =
        publisherQueries.selectAllPublishers { publisherUuid, publisherName, publisherUrl, publisherLogo, publisherDescription ->
            Publisher(
                uuid = publisherUuid,
                name = publisherName,
                url = publisherUrl,
                logo = publisherLogo,
                description = publisherDescription
            )
        }.asFlow().mapToList(context)

internal fun AudiobooksByDB.getPublishersByUuid(publisherUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<Publisher> =
    publisherQueries.selectPublisherByUuid(publisherUuid) { publisherUuid, publisherName, publisherUrl, publisherLogo, publisherDescription ->
        Publisher(
            uuid = publisherUuid,
            name = publisherName,
            url = publisherUrl,
            logo = publisherLogo,
            description = publisherDescription
        )
    }.asFlow().mapToOne(context)
