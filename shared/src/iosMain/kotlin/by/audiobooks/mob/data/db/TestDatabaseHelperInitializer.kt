package by.audiobooks.mob.data.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class TestDatabaseHelperInitializer {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AudiobooksByDB.Schema, "audiobooks-by.db")
    }
}