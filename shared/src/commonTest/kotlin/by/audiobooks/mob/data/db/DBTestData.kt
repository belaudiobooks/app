package by.audiobooks.mob.data.db

import by.audiobooks.mob.data.network.dto.Availability
import by.audiobooks.mob.data.network.dto.Book
import by.audiobooks.mob.data.network.dto.Data
import by.audiobooks.mob.data.network.dto.Gender
import by.audiobooks.mob.data.network.dto.Language
import by.audiobooks.mob.data.network.dto.Link
import by.audiobooks.mob.data.network.dto.LinkType
import by.audiobooks.mob.data.network.dto.Narration
import by.audiobooks.mob.data.network.dto.Person
import by.audiobooks.mob.data.network.dto.Publisher
import by.audiobooks.mob.data.network.dto.Tag
import kotlinx.datetime.LocalDate

object DBTestData {

    val testDataSnapshot: Data = Data(
        books = listOf(
            Book(
                uuid = "977e535e-1e2a-4c95-bf3b-629ff80aee94",
                title = "Першая Кніга",
                description = "Першая Кніга",
                descriptionSource = "https://books.com/",
                authors = listOf("e50f35f5-c134-4c82-90cc-8391fd676f3d"),
                slug = "pershaya-kniga",
                tag = listOf(0L, 2L),
                narrations = listOf(
                    Narration(
                        uuid = "5942b109-714a-477c-8266-d776c7f2fbd7",
                        narrators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                        links = listOf(
                            Link(
                                url = "https://youtube.com/pershaya-kniga",
                                urlType = 0L
                            ),
                            Link(
                                url = "https://kobo.com/pershaya-kniga",
                                urlType = 1L
                            ),
                        ),
                        duration = 32.0,
                        publishers = listOf("e82f5ab3-6c85-4d0a-8389-91a4ea484dae"),
                        paid = true,
                        language = Language.BELARUSIAN,
                        translators = listOf(),
                        coverImage = "http://images.com/pershaya-kniga",
                        coverImageSource = "http://images.com/",
                        date = LocalDate.parse("2000-01-01"),
                        description = "Першая Кніга",
                        previewUrl = ""
                    )
                )
            ),
            Book(
                uuid = "98c04aca-9eae-4e45-aaf6-39f69aaf9da1",
                title = "Другая Кніга",
                description = "Другая Кніга",
                descriptionSource = "https://books.com/",
                authors = listOf("70068e4b-45f1-4298-b099-f75f87bde4be"),
                slug = "drugaya-kniga",
                tag = listOf(1L),
                narrations = listOf(
                    Narration(
                        uuid = "1b814745-b578-454c-ad90-a894c1594a10",
                        narrators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                        links = listOf(
                            Link(
                                url = "https://youtube.com/drugaya-kniga",
                                urlType = 0L
                            ),
                            Link(
                                url = "https://kobo.com/drugaya-kniga",
                                urlType = 1L
                            ),
                        ),
                        duration = 64.0,
                        publishers = listOf("4a85b0e5-917d-40f8-ad43-7119251951d5"),
                        paid = true,
                        language = Language.BELARUSIAN,
                        translators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                        coverImage = "http://images.com/drugaya-kniga",
                        coverImageSource = "http://images.com/",
                        date = LocalDate.parse("2001-01-01"),
                        description = "Другая Кніга",
                        previewUrl = ""
                    )
                )
            ),
            Book(
                uuid = "5cf6f8bd-796c-43f8-9b32-8cd3dd58ab70",
                title = "Трэцяя Кніга",
                description = "Трэцяя Кніга",
                descriptionSource = "https://books.com/",
                authors = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                slug = "trecaya-kniga",
                tag = listOf(2L),
                narrations = listOf(
                    Narration(
                        uuid = "b44c0f0d-1543-4785-b62c-245e2da0b7c1",
                        narrators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                        links = listOf(
                            Link(
                                url = "https://youtube.com/trecaya-kniga",
                                urlType = 0L
                            ),
                            Link(
                                url = "https://apple.com/books/trecaya-kniga",
                                urlType = 3L
                            ),
                            Link(
                                url = "https://kobo.com/trecaya-kniga",
                                urlType = 1L
                            ),
                        ),
                        duration = 128.0,
                        publishers = listOf("216bb92b-2e19-4f2c-b24e-8f361d8e793b"),
                        paid = false,
                        language = Language.BELARUSIAN,
                        translators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                        coverImage = "http://images.com/trecaya-kniga",
                        coverImageSource = "http://images.com/",
                        date = LocalDate.parse("2002-01-01"),
                        description = "Трэцяя Кніга",
                        previewUrl = ""
                    )
                )
            )
        ),
        people = listOf(
            Person(
                uuid = "e50f35f5-c134-4c82-90cc-8391fd676f3d",
                name = "Кава Кава Кава",
                description = "Кава Кава Кава",
                descriptionSource = "https://people.com/",
                photo = "https://people.com/photos/kava-kava-kava.jpg",
                photoSource = "https://people.com/",
                slug = "kava-kava-kava",
                gender = Gender.PLURAL
            ),
            Person(
                uuid = "a240893c-a042-45fb-bcca-edee57961917",
                name = "Ганс Ганна Ганнаўна",
                description = "Ганс Ганна Ганнаўна",
                descriptionSource = "https://people.com/",
                photo = "https://people.com/photos/guns-ganna-gannauna.jpg",
                photoSource = "https://people.com/",
                slug = "guns-ganna-gannauna",
                gender = Gender.FEMALE
            ),
            Person(
                uuid = "70068e4b-45f1-4298-b099-f75f87bde4be",
                name = "Андрэеў Андрэй Андрэевіч",
                description = "Андрэеў Андрэй Андрэевіч",
                descriptionSource = "https://people.com/",
                photo = "https://people.com/photos/andreeu-andrey-andreevich.jpg",
                photoSource = "https://people.com/",
                slug = "andreeu-andrey-andreevich",
                gender = Gender.MALE
            )
        ),
        linkTypes = listOf(
            LinkType(
                id = 0L,
                name = "YouTube",
                caption = "YouTube",
                icon = "https://youtube.com/logo.png",
                availability = Availability.EVERYWHERE
            ),
            LinkType(
                id = 1L,
                name = "Kobo",
                caption = "Kobo",
                icon = "https://kobo.com/logo.png",
                availability = Availability.EVERYWHERE
            ),
            LinkType(
                id = 2L,
                name = "Spotify",
                caption = "Spotify",
                icon = "https://spotify.com/logo.png",
                availability = Availability.USA_ONLY
            ),
            LinkType(
                id = 3L,
                name = "Apple Books",
                caption = "Apple_Books",
                icon = "https://apple.com/books/logo.png",
                availability = Availability.UNAVAILABLE_IN_BELARUS
            )
        ),
        tags = listOf(
            Tag(
                id = 0L,
                name = "Паэзія",
                slug = "paezija",
                description = "Паэзія"
            ),
            Tag(
                id = 1L,
                name = "Фантастыка і фэнтэзі",
                slug = "fantastyka-i-fentezi",
                description = "Фантастыка і фэнтэзі"
            ),
            Tag(
                id = 2L,
                name = "Дзецям і падлеткам",
                slug = "dzieciam-i-padletkam",
                description = "Дзецям і падлеткам"
            )
        ),
        publishers = listOf(
            Publisher(
                uuid = "e82f5ab3-6c85-4d0a-8389-91a4ea484dae",
                name = "Выдавецтва_1",
                slug = "vydavectva_1",
                url = "https://vydavectva_1.com/",
                logo = "https://vydavectva_1.com/logo.jpg",
                description = "Апісанне Выдавецтва_1"
            ),
            Publisher(
                uuid = "aed9e934-6d9a-4e9d-9e7e-56cc11282fb1",
                name = "Выдавецтва_2",
                slug = "vydavectva_2",
                url = "https://vydavectva_2.com/",
                logo = "https://vydavectva_2.com/logo.jpg",
                description = "Апісанне Выдавецтва_2"
            ),
            Publisher(
                uuid = "216bb92b-2e19-4f2c-b24e-8f361d8e793b",
                name = "Выдавецтва_3",
                slug = "vydavectva_3",
                url = "https://vydavectva_3.com/",
                logo = "https://vydavectva_3.com/logo.jpg",
                description = "Апісанне Выдавецтва_3"
            ),
            Publisher(
                uuid = "4a85b0e5-917d-40f8-ad43-7119251951d5",
                name = "Выдавецтва_4",
                slug = "vydavectva_4",
                url = "https://vydavectva_4.com/",
                logo = "https://vydavectva_4.com/logo.jpg",
                description = "Апісанне Выдавецтва_4"
            ),
            Publisher(
                uuid = "bf6c7351-4ecf-4c86-912e-237e5efa0e14",
                name = "Выдавецтва_5",
                slug = "vydavectva_5",
                url = "https://vydavectva_5.com/",
                logo = "https://vydavectva_5.com/logo.jpg",
                description = "Апісанне Выдавецтва_5"
            )
        )
    )

    val extraPublisher = Publisher(
        uuid = "78b49431-4829-4408-8f5e-dc3a0577eaf5",
        name = "Выдавецтва_6",
        slug = "vydavectva_6",
        url = "https://vydavectva_6.com/",
        logo = "https://vydavectva_6.com/logo.jpg",
        description = "Апісанне Выдавецтва_6"
    )

    val extraTag = Tag(
        id = 3L,
        name = "Чытае аўтар",
        slug = "cytaje-autar",
        description = "Чытае аўтар"
    )

    val extraLinkType = LinkType(
        id = 4L,
        name = "Castbox",
        caption = "Castbox",
        icon = "https://castbox.com/books/logo.png",
        availability = Availability.UNAVAILABLE_IN_BELARUS
    )

    val extraPerson = Person(
        uuid = "9e8fd1ae-13a8-4df9-980d-bbe5752cff87",
        name = "Ніхтонаў Ніхто Ніхтонавіч",
        description = "Ніхтонаў Ніхто Ніхтонавіч",
        descriptionSource = "https://people.com/",
        photo = "https://people.com/photos/nihtonay-nihto-nihtonavich.jpg",
        photoSource = "https://people.com/",
        slug = "nihtonay-nihto-nihtonavich",
        gender = Gender.PLURAL
    )

    val extraBook = Book(
        uuid = "14196350-090b-4d63-a964-920773ba1df2",
        title = "Яшчэ Адна Кніга",
        description = "Яшчэ Адна Кніга",
        descriptionSource = "https://books.com/",
        authors = listOf("e50f35f5-c134-4c82-90cc-8391fd676f3d", "70068e4b-45f1-4298-b099-f75f87bde4be"),
        slug = "yashce-adna-kniga",
        tag = listOf(0L),
        narrations = listOf(
            Narration(
                uuid = "7f7ab832-9b6c-430b-8ee5-191ce89567c7",
                narrators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                links = listOf(
                    Link(
                        url = "https://youtube.com/yashce-adna-kniga",
                        urlType = 0L
                    ),
                    Link(
                        url = "https://apple.com/books/yashce-adna-kniga",
                        urlType = 3L
                    ),
                    Link(
                        url = "https://kobo.com/yashce-adna-kniga",
                        urlType = 1L
                    ),
                ),
                duration = 256.0,
                publishers = listOf("bf6c7351-4ecf-4c86-912e-237e5efa0e14"),
                paid = true,
                language = Language.BELARUSIAN,
                translators = listOf("a240893c-a042-45fb-bcca-edee57961917"),
                coverImage = "http://images.com/yashce-adna-kniga",
                coverImageSource = "http://images.com/",
                date = LocalDate.parse("2004-01-01"),
                description = "Яшчэ Адна Кніга",
                previewUrl = ""
            )
        )
    )
}