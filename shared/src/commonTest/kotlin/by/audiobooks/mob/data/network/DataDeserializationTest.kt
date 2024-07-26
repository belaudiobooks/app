package by.audiobooks.mob.data.network

import by.audiobooks.mob.data.network.SiteApi.Companion.DESERIALIZATION_SETTINGS
import by.audiobooks.mob.data.network.dto.BackendDataSnapshot
import kotlin.test.Test
import kotlin.test.assertTrue

class DataDeserializationTest {

    @Test
    fun deserializationTest() {

        val data = DESERIALIZATION_SETTINGS.decodeFromString<BackendDataSnapshot>(DataJsonExample.data)

        assertTrue {
            data.books.isNotEmpty()
        }
    }
}


object DataJsonExample {
    const val data = """
{
  "books": [
    {
      "uuid": "f9c17e32-7428-46eb-b2a7-41fab8bce1c9",
      "title": "Кніга 1",
      "description": "Цікавая Кніга 1",
      "description_source": "Беларускі цэнтр;https://belcenter.com/",
      "authors": [
        "d99fad75-eb79-4d15-b8bf-2bdacae937f7"
      ],
      "slug": "kniha-1",
      "tag": [
        1,
        2
      ],
      "narrations": [
        {
          "uuid": "2230e08d-69bc-44fa-bfd5-cffd58ba7a37",
          "narrators": [
            "520ed5b8-4996-424c-8aa1-f03fc54ed1c5"
          ],
          "links": [
            {
              "url": "https://anotheraudiolib.com/kniha-1",
              "url_type": 2
            }
          ],
          "duration": null,
          "publishers": [
            "a9c6d9de-435d-4d95-b3e1-cb578e0b4b28"
          ],
          "paid": false,
          "language": "BELARUSIAN",
          "translators": [],
          "cover_image": null,
          "cover_image_source": "",
          "date": "2021-01-01",
          "description": "",
          "preview_url": ""
        }
      ]
    },
    {
      "uuid": "1d9c7f0d-1542-4918-882a-01d0a196c5e3",
      "title": "Кніга 2",
      "description": "Цікавая Кніга 2",
      "description_source": "Беларускі цэнтр;https://belcenter.com/",
      "authors": [
        "002a69ec-6913-48d4-94cc-f49aa1cf605a"
      ],
      "slug": "kniha-2",
      "tag": [
        3
      ],
      "narrations": [
        {
          "uuid": "f61e180e-3c61-433d-ad23-63891500c3ed",
          "narrators": [
            "002a69ec-6913-48d4-94cc-f49aa1cf605a"
          ],
          "links": [
            {
              "url": "https://usaaudiolib.com/kniha-2",
              "url_type": 3
            }
          ],
          "duration": 2520.0,
          "publishers": [
            "7a4414db-f8bf-4648-870f-578513112df9"
          ],
          "paid": false,
          "language": "BELARUSIAN",
          "translators": [
            "002a69ec-6913-48d4-94cc-f49aa1cf605a"
          ],
          "cover_image": null,
          "cover_image_source": "",
          "date": "2022-02-02",
          "description": "",
          "preview_url": ""
        }
      ]
    },
    {
      "uuid": "00990d77-8157-41d3-b18e-e6861c692db3",
      "title": "Кніга 3",
      "description": "Цікавая Кніга 3",
      "description_source": "Беларускі цэнтр;https://belcenter.com/",
      "authors": [
        "520ed5b8-4996-424c-8aa1-f03fc54ed1c5"
      ],
      "slug": "kniha-3",
      "tag": [
        2
      ],
      "narrations": [
        {
          "uuid": "21efdfba-aae1-4bfb-b73a-ee777ff62d82",
          "narrators": [
            "d99fad75-eb79-4d15-b8bf-2bdacae937f7"
          ],
          "links": [
            {
              "url": "https://audiolib.com/kniha-3",
              "url_type": 1
            },
            {
              "url": "https://anotheraudiolib.com/kniha-3",
              "url_type": 2
            },
            {
              "url": "https://usaaudiolib.com/kniha-3",
              "url_type": 3
            }
          ],
          "duration": 11640.0,
          "publishers": [
            "2b65e1c5-2f38-4a71-a364-9e8f35d5e379"
          ],
          "paid": true,
          "language": "BELARUSIAN",
          "translators": [],
          "cover_image": "https://lib.com/books_media/covers/kniha-3.jpg",
          "cover_image_source": "",
          "date": "2023-03-03",
          "description": "",
          "preview_url": "https://youtu.be/btrmAL6Go0I"
        }
      ]
    }
  ],
  "people": [
    {
      "uuid": "d99fad75-eb79-4d15-b8bf-2bdacae937f7",
      "name": "Галіна Галяроўская",
      "description": "",
      "description_source": "",
      "photo": null,
      "photo_source": "",
      "slug": "galina-galarouskaya",
      "gender": "FEMALE"
    },
    {
      "uuid": "002a69ec-6913-48d4-94cc-f49aa1cf605a",
      "name": "Алег Алегагаевіч разам з Васілем Васілевічам",
      "description": "",
      "description_source": "",
      "photo": null,
      "photo_source": "",
      "slug": "aleh-and-vasily",
      "gender": "PLURAL"
    },
    {
      "uuid": "520ed5b8-4996-424c-8aa1-f03fc54ed1c5",
      "name": "Ігар Ігаровавіч",
      "description": "",
      "description_source": "",
      "photo": "https://faces.com/photos/ihar-iharovavich.jpg",
      "photo_source": "",
      "slug": "ihar-iharovavich",
      "gender": "MALE"
    }
  ],
  "link_types": [
    {
      "id": 1,
      "name": "audiolib",
      "caption": "Звычайная Аудыекнігарня",
      "icon": "https://audiolib.com/logo.png",
      "availability": "EVERYWHERE"
    },
    {
      "id": 2,
      "name": "anotheraudiolib",
      "caption": "Незвычайная Аудыекнігарня",
      "icon": "https://anotheraudiolib.com/logo.png",
      "availability": "UNAVAILABLE_IN_BELARUS"
    },
    {
      "id": 3,
      "name": "usaaudiolib",
      "caption": "Амерыканская Аудыекнігарня",
      "icon": "https://usaaudiolib.com/logo.png",
      "availability": "USA_ONLY"
    }
  ],
  "tags": [
    {
      "id": 1,
      "name": "Фантастыка",
      "slug": "fantastyka",
      "description": ""
    },
    {
      "id": 2,
      "name": "Проза",
      "slug": "proza",
      "description": ""
    },
    {
      "id": 3,
      "name": "Паэзія",
      "slug": "paezija",
      "description": ""
    }
  ],
  "publishers": [
    {
      "uuid": "a9c6d9de-435d-4d95-b3e1-cb578e0b4b28",
      "name": "Першае выдавецтва",
      "slug": "pershae-vydavectva",
      "url": "https://pershae-vydavectva.by/about",
      "logo": "https://logos.com/logos/pershae-vydavectva.png",
      "description": "Мы - Першае выдавецтва!"
    },
    {
      "uuid": "7a4414db-f8bf-4648-870f-578513112df9",
      "name": "Другое выдавецтва",
      "slug": "drugoe-vydavectva",
      "url": "https://drugoe-vydavectva.by/about",
      "logo": "https://logos.com/logos/drugoe-vydavectva.png",
      "description": "Мы - Другое выдавецтва!"
    },
    {
      "uuid": "2b65e1c5-2f38-4a71-a364-9e8f35d5e379",
      "name": "Трэцяе выдавецтва",
      "slug": "trecyae-vydavectva",
      "url": "https://trecyae-vydavectva.by/about",
      "logo": "https://logos.com/logos/trecyae-vydavectva.png",
      "description": "Мы - Трэцяе выдавецтва!"
    }
  ]
}
    """
}
