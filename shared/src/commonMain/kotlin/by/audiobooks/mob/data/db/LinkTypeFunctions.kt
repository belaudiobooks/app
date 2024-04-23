package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Availability
import by.audiobooks.mob.domain.LinkType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllLinkTypes(context: CoroutineContext = Dispatchers.IO): Flow<List<LinkType>> =
    linkTypeQueries.selectAllLinkTypes { linkTypeId, linkTypeName, linkTypeCaption, linkTypeIcon, linkTypeAvailability ->
        LinkType(
            id = linkTypeId,
            name = linkTypeName,
            caption = linkTypeCaption,
            icon = linkTypeIcon,
            availability = Availability.valueOf(linkTypeAvailability)
        )
    }.asFlow().mapToList(context)
