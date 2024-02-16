package by.audiobooks.mob.cache

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver


/**
 * Init repository for Android platform.
 */
actual fun getTestRepository(): Repository {
    val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    AudiobooksByDB.Schema.create(sqlDriver)
    return Repository(sqlDriver)
}
