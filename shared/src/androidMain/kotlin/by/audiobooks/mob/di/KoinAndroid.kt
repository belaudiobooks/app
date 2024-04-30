package by.audiobooks.mob.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import by.audiobooks.mob.data.db.AudiobooksByDB
import org.koin.dsl.module

actual val platformModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(AudiobooksByDB.Schema, get(), "audiobooks-by.db")
    }
}
