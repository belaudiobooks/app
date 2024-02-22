package by.audiobooks.mob.cache

import by.audiobooks.mob.model.source.Gender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun AudiobooksByDB.deleteAllPersons() = withContext(Dispatchers.Default) {
    personQueries.transaction {
        personQueries.deleteAllPerson()
    }
}

internal suspend fun AudiobooksByDB.insertPerson(person: by.audiobooks.mob.model.source.Person) = withContext(Dispatchers.Default) {
    personQueries.transaction {
        personQueries.insertPerson(
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
}

internal suspend fun AudiobooksByDB.getAllPersons(): List<by.audiobooks.mob.model.source.Person> = withContext(Dispatchers.Default) {
    personQueries.selectAllPerson { uuid, name, description, description_source, photo, photo_source, slug, gender ->
        by.audiobooks.mob.model.source.Person(
            uuid = uuid,
            name = name,
            description = description,
            descriptionSource = description_source,
            photo = photo,
            photoSource = photo_source,
            slug = slug,
            gender = Gender.valueOf(gender)
        )
    }.executeAsList()
}

internal suspend fun AudiobooksByDB.getPersonByUuid(uuid: String): by.audiobooks.mob.model.source.Person = withContext(Dispatchers.Default) {
    val dbPerson = personQueries.selectPersonByUuid(person_uuid = uuid).executeAsOne()
    return@withContext by.audiobooks.mob.model.source.Person(
        uuid = dbPerson.uuid,
        name = dbPerson.name,
        description = dbPerson.description,
        descriptionSource = dbPerson.description_source,
        photo = dbPerson.photo,
        photoSource = dbPerson.photo_source,
        slug = dbPerson.slug,
        gender = Gender.valueOf(dbPerson.gender)
    )
}
