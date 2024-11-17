package by.audiobooks.mob.data.db

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import by.audiobooks.mob.domain.Gender
import by.audiobooks.mob.domain.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

internal fun AudiobooksByDB.getAllPersons(context: CoroutineContext = Dispatchers.IO): Flow<List<Person>> =
    personQueries.selectAllPersons { personUuid, personName, personDescription, personDescriptionSource, personPhoto, personPhotoSource, personGender ->
        Person(
            uuid = personUuid,
            name = personName,
            description = personDescription,
            descriptionSource = personDescriptionSource,
            photo = personPhoto,
            photoSource = personPhotoSource,
            gender = Gender.valueOf(personGender)
        )
    }.asFlow().mapToList(context)

internal fun AudiobooksByDB.getPersonByUuid(personUuid: String): Person? =
    getPersonByUuidQuery(personUuid).executeAsOneOrNull()

internal fun AudiobooksByDB.getPersonByUuidSubscription(personUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<Person> =
    getPersonByUuidQuery(personUuid).asFlow().mapToOne(context)

private fun AudiobooksByDB.getPersonByUuidQuery(personUuid: String): Query<Person> {
    return personQueries.selectPersonByUuid(personUuid) { personUuid, personName, personDescription, personDescriptionSource, personPhoto, personPhotoSource, personGender ->
        Person(
            uuid = personUuid,
            name = personName,
            description = personDescription,
            descriptionSource = personDescriptionSource,
            photo = personPhoto,
            photoSource = personPhotoSource,
            gender = Gender.valueOf(personGender)
        )
    }
}

internal fun AudiobooksByDB.getAuthorsByBookUuidSubscription(bookUuid: String, context: CoroutineContext = Dispatchers.IO): Flow<List<Person>> =
    personQueries.selectAuthorsByBookUuid(bookUuid).asFlow().mapToList(context)
        .map { it.map { person -> personMapper(person) } }

internal fun AudiobooksByDB.getAuthorsByBookUuid(bookUuid: String): List<Person> =
    personQueries.selectAuthorsByBookUuid(bookUuid)
        .executeAsList()
        .map { person -> personMapper(person) }

private fun personMapper(record: SelectAuthorsByBookUuid): Person =
    Person(
        uuid = record.personUuid,
        name = record.personName,
        description = record.personDescription,
        descriptionSource = record.personDescriptionSource,
        photo = record.personPhoto,
        photoSource = record.personPhotoSource,
        gender = Gender.valueOf(record.personGender)
    )
