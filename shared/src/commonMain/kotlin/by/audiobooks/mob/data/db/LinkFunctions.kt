package by.audiobooks.mob.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import by.audiobooks.mob.domain.Link
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
