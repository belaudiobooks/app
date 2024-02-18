package by.audiobooks.mob.cache

import app.cash.sqldelight.db.SqlDriver

class Repository(sqlDriver: SqlDriver) {

    internal val database by lazy { AudiobooksByDB(sqlDriver) }

}