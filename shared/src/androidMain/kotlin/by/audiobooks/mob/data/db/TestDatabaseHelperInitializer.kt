package by.audiobooks.mob.data.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class TestDatabaseHelperInitializer(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AudiobooksByDB.Schema, context, "audiobooks-by.db")
    }
}