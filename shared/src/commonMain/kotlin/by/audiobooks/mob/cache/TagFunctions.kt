package by.audiobooks.mob.cache

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun AudiobooksByDB.deleteAllTags() = withContext(Dispatchers.Default) {
    tagQueries.transaction {
        tagQueries.deleteAllTags()
    }
}

internal suspend fun AudiobooksByDB.insertTag(tag: by.audiobooks.mob.model.source.Tag) = withContext(Dispatchers.Default) {
    tagQueries.insertTag(
        id = tag.id,
        name = tag.name,
        slug = tag.slug,
        description = tag.description
    )
}

internal suspend fun AudiobooksByDB.getAllTags(): List<by.audiobooks.mob.model.source.Tag> = withContext(Dispatchers.Default) {
    tagQueries.selectAllTags { id, name, slug, description ->
        by.audiobooks.mob.model.source.Tag(
            id, name, slug, description
        )
    }.executeAsList()
}

internal suspend fun AudiobooksByDB.getTagById(tagId: Long): by.audiobooks.mob.model.source.Tag = withContext(Dispatchers.Default) {
    val dbTag = tagQueries.slectTagById(tagId).executeAsOne()
    return@withContext by.audiobooks.mob.model.source.Tag(
        id = dbTag.id,
        name = dbTag.name,
        slug = dbTag.slug,
        description = dbTag.description
    )
}