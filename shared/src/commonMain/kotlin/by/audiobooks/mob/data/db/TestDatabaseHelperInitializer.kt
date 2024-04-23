package by.audiobooks.mob.data.db

import app.cash.sqldelight.db.SqlDriver

expect class TestDatabaseHelperInitializer {

    fun createDriver(): SqlDriver

}