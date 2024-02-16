package by.audiobooks.mob.cache

import app.cash.sqldelight.driver.native.NativeSqliteDriver


/**
 * Init repository for iOS platform.
 */
actual fun getTestRepository(): Repository {
    val sqlDriver = NativeSqliteDriver(AudiobooksByDB.Schema, "test-audiobooks-by.db")
    return Repository(sqlDriver)
}
