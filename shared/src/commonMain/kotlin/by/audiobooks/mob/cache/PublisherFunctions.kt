package by.audiobooks.mob.cache

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun AudiobooksByDB.deleteAllPublishers() = withContext(Dispatchers.Default) {
    publisherQueries.transaction {
        publisherQueries.deleteAllPublishers()
    }
}

internal suspend fun AudiobooksByDB.insertPublisher(publisher: by.audiobooks.mob.model.source.Publisher) = withContext(Dispatchers.Default) {
    publisherQueries.transaction {
        publisherQueries.insertPublisher(
            uuid = publisher.uuid,
            name = publisher.name,
            slug = publisher.slug,
            url = publisher.url,
            logo = publisher.logo,
            description = publisher.description
        )
    }
}

internal suspend fun AudiobooksByDB.getAllPublishers(): List<by.audiobooks.mob.model.source.Publisher> =
    withContext(Dispatchers.Default) {
        publisherQueries.selectAllPublishers { uuid, name, slug, url, logo, description ->
            by.audiobooks.mob.model.source.Publisher(
                uuid = uuid,
                name = name,
                slug = slug,
                url = url,
                logo = logo,
                description = description
            )
        }.executeAsList()
    }

internal suspend fun AudiobooksByDB.getPublisherByUuid(uuid: String): by.audiobooks.mob.model.source.Publisher = withContext(Dispatchers.Default) {
    val dbPublisher = publisherQueries.selectPublisherByUuid(publisher_uuid = uuid).executeAsOne()
    return@withContext by.audiobooks.mob.model.source.Publisher(
        uuid = dbPublisher.uuid,
        name = dbPublisher.name,
        slug = dbPublisher.slug,
        url = dbPublisher.url,
        logo = dbPublisher.logo,
        description = dbPublisher.description
    )
}
