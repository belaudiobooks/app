package by.audiobooks.mob.domain

data class Person(
    val uuid: String,
    val name: String,
    val description: String,
    val descriptionSource: String,
    val photo: String = "",
    val photoSource: String,
    val gender: Gender
)
