package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Availability
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun AudiobooksByDB.deleteAllLinkTypes() = withContext(Dispatchers.Default) {
    linkTypeQueries.transaction {
        linkTypeQueries.deleteAllLinkTypes()
    }
}

internal suspend fun AudiobooksByDB.insertLinkType(linkType: by.audiobooks.mob.model.source.LinkType) = withContext(Dispatchers.Default) {
    linkTypeQueries.transaction {
        linkTypeQueries.insertLinkType(
            id = linkType.id,
            name = linkType.name,
            caption = linkType.caption,
            icon = linkType.icon,
            availability = linkType.availability.name
        )
    }
}

internal suspend fun AudiobooksByDB.getAllLinkTypes(): List<by.audiobooks.mob.model.source.LinkType> = withContext(Dispatchers.Default) {
    linkTypeQueries.selectAllLinkTypes { id, name, caption, icon, availability ->
        by.audiobooks.mob.model.source.LinkType(
            id = id,
            name = name,
            caption = caption,
            icon = icon,
            availability = Availability.valueOf(availability)
        )
    }.executeAsList()
}

internal suspend fun AudiobooksByDB.getLinkTypeById(linkTypeId: Long): by.audiobooks.mob.model.source.LinkType = withContext(Dispatchers.Default) {
    val dbLinkType = linkTypeQueries.selectLinkTypeById(linkTypeId).executeAsOne()
    return@withContext by.audiobooks.mob.model.source.LinkType(
        id = dbLinkType.id,
        name = dbLinkType.name,
        caption = dbLinkType.caption,
        icon = dbLinkType.icon,
        availability = Availability.valueOf(dbLinkType.availability)
    )
}
