package by.audiobooks.mob.data.db

import by.audiobooks.mob.data.network.dto.Book
import by.audiobooks.mob.data.network.dto.Data
import by.audiobooks.mob.data.network.dto.LinkType
import by.audiobooks.mob.data.network.dto.Person
import by.audiobooks.mob.data.network.dto.Publisher
import by.audiobooks.mob.data.network.dto.Tag

fun AudiobooksByDB.replaceData(data: Data) {
    editQueries.transaction {
        cleanUpDB()
        data.linkTypes.forEach { insertLinkType(it) }
        data.tags.forEach { insertTag(it) }
        data.people.forEach { insertPerson(it) }
        data.publishers.forEach { insertPublisher(it) }
        data.books.forEach { insertBook(it) }
    }
}

internal fun AudiobooksByDB.cleanUpDB() {
        editQueries.deleteAllNarrationPublishers()
        editQueries.deleteAllNarrationTranslators()
        editQueries.deleteAllNarrationNarrators()
        editQueries.deleteAllNarrationLinks()
        editQueries.deleteAllNarrations()
        editQueries.deleteAllBookTags()
        editQueries.deleteAllBookAuthors()
        editQueries.deleteAllBooks()
        editQueries.deleteAllPublishers()
        editQueries.deleteAllPerson()
        editQueries.deleteAllTags()
        editQueries.deleteAllLinkTypes()
}

internal fun AudiobooksByDB.insertLinkType(linkType: LinkType) {
        editQueries.insertLinkType(
            id = linkType.id,
            name = linkType.name,
            caption = linkType.caption,
            icon = linkType.icon,
            availability = linkType.availability.name
        )
}

internal fun AudiobooksByDB.insertTag(tag: Tag) {
        editQueries.insertTag(
            id = tag.id,
            name = tag.name,
            slug = tag.slug,
            description = tag.description
        )
}

internal fun AudiobooksByDB.insertPerson(person: Person) {
    editQueries.insertPerson(
            uuid = person.uuid,
            name = person.name,
            description = person.description,
            description_source = person.descriptionSource,
            photo = person.photo,
            photo_source = person.photoSource,
            slug = person.slug,
            gender = person.gender.name
        )
}

internal fun AudiobooksByDB.insertPublisher(publisher: Publisher) {
    editQueries.insertPublisher(
        uuid = publisher.uuid,
        name = publisher.name,
        slug = publisher.slug,
        url = publisher.url,
        logo = publisher.logo,
        description = publisher.description
    )
}

internal fun AudiobooksByDB.insertBook(book: Book) {
    editQueries.insertBook(
        uuid = book.uuid,
        title = book.title,
        description = book.description,
        description_source = book.descriptionSource,
        slug = book.slug
    )
    book.authors.forEachIndexed { idx, authorUuid ->
        editQueries.addBookAuthor(
            book_uuid = book.uuid,
            person_uuid = authorUuid,
            priority = idx.toLong()
        )
    }
    book.tag.forEach {
        editQueries.addBookTag(
            book_uuid = book.uuid,
            tag_id = it
        )
    }
    book.narrations.forEach {
        editQueries.insertNarration(
            uuid = it.uuid,
            book_uuid = book.uuid,
            duration = it.duration,
            paid = it.paid.toString(),
            language = it.language.name,
            cover_image = it.coverImage,
            cover_image_source = it.coverImageSource,
            date = it.date.toString(),
            description = it.description,
            preview_url = it.previewUrl
        )
        it.narrators.forEachIndexed { idx, narratorUuid ->
            editQueries.addNarrator(
                narration_uuid = it.uuid,
                person_uuid = narratorUuid,
                priority = idx.toLong()
            )
        }
        it.translators.forEachIndexed { idx, translatorUuid ->
            editQueries.addTranslator(
                narration_uuid = it.uuid,
                person_uuid = translatorUuid,
                priority = idx.toLong()
            )
        }
        it.publishers.forEachIndexed { idx, publisherUuid ->
            editQueries.addPublisher(
                narration_uuid = it.uuid,
                publisher_uuid = publisherUuid,
                priority = idx.toLong()
            )
        }
        it.links.forEach { link ->
            editQueries.addNarrationLink(
                narration_uuid = it.uuid,
                url = link.url,
                url_type =link.urlType
            )
        }
    }
}
