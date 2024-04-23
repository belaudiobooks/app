package by.audiobooks.mob.data.db

import app.cash.sqldelight.driver.native.NativeSqliteDriver

/**
 * Initializer of test [DatabaseHelper] for iOS platform.
 */
actual fun getTestDatabaseHelper(): DatabaseHelper {
    val sqlDriver = NativeSqliteDriver(
        schema = AudiobooksByDB.Schema,
        name = "test-audiobooks-by.db",
        onConfiguration = {
            it.copy(
                inMemory = true
            )
        }
    )
    return DatabaseHelper(sqlDriver)
}