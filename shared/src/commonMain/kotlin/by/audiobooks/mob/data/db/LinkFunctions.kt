package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Availability
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllLinks(context: CoroutineContext = Dispatchers.IO): Flow<List<Link>> =
    linkQueries.selectAllLinks { narrationUuid, url, linkTypeId -> Link(
        narrationUuid = narrationUuid,
        url = url,
        linkTypeId = linkTypeId
    ) }.asFlow().mapToList(context)

internal fun AudiobooksByDB.getLinksDetailsByNarrationUuid(narrationUuid: String): List<LinkDetails> =
    linkQueries.selectLinkDetailsByNarrationUuid(narrationUuid) {
            narrationUuid,
            narrationLink,
            linkTypeId,
            linkTypeName,
            linkTypeIcon,
            linkTypeCaption,
            linkTypeAvailability ->
        LinkDetails(
            narrationUuid = narrationUuid,
            url = narrationLink,
            linkTypeId = linkTypeId,
            linkTypeName = linkTypeName,
            linkTypeIcon = linkTypeIcon,
            linkTypeCaption = linkTypeCaption,
            linkTypeAvailability = Availability.valueOf(linkTypeAvailability)
        )
    }.executeAsList()
