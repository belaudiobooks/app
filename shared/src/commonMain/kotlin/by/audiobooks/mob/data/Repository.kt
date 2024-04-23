package by.audiobooks.mob.data

import by.audiobooks.mob.domain.Book
import by.audiobooks.mob.domain.Link
import by.audiobooks.mob.domain.LinkType
import by.audiobooks.mob.domain.Narration
import by.audiobooks.mob.domain.Person
import by.audiobooks.mob.domain.Publisher
import by.audiobooks.mob.domain.Tag
import kotlinx.coroutines.flow.Flow

interface Repository {

    /**
     * Asynchronously update db from remote server.
     */
    suspend fun refreshData()

    /**
     * Flow of all [Book] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllBooks(): Flow<List<Book>>

    /**
     * Flow of all [Narration] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllNarrations(): Flow<List<Narration>>

    /**
     * Flow of all [Person] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllPersons(): Flow<List<Person>>

    /**
     * Flow of all [Publisher] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllPublishers(): Flow<List<Publisher>>

    /**
     * Flow of all [Tag] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllTags(): Flow<List<Tag>>

    /**
     * Flow of all [Link] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllLinks(): Flow<List<Link>>

    /**
     * Flow of all [LinkType] records from database. It emits a new list every
     * time the database changes for underlying query.
     */
    fun getAllLinkTypes(): Flow<List<LinkType>>

}