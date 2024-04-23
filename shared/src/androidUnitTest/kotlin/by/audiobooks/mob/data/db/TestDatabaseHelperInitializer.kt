package by.audiobooks.mob.data.db

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

/**
 * Initializer of test [DatabaseHelper] for Andoid platform.
 */
actual fun getTestDatabaseHelper(): DatabaseHelper {
    val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    AudiobooksByDB.Schema.create(sqlDriver)
    return DatabaseHelper(sqlDriver)
}
