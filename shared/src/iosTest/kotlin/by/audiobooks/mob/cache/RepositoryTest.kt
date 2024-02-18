package by.audiobooks.mob.cache

import app.cash.sqldelight.driver.native.NativeSqliteDriver


/**
 * Init repository for iOS platform.
 */
actual fun getTestRepository(): Repository {
    val sqlDriver = NativeSqliteDriver(
        schema = AudiobooksByDB.Schema,
        name = "test-audiobooks-by.db",
        onConfiguration = {
            it.copy(
                inMemory = true
            )
        }
    )
    return Repository(sqlDriver)
}
