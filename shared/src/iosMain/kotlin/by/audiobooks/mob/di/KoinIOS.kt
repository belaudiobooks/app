package by.audiobooks.mob.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.data.RepositoryImpl
import by.audiobooks.mob.data.db.AudiobooksByDB
import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.AlgoliaSearchApi
import by.audiobooks.mob.data.network.SiteApi

fun getRepositoryClient(): Repository {
    return RepositoryImpl(
        dbHelper = DatabaseHelper(
            sqlDriver = NativeSqliteDriver(
                AudiobooksByDB.Schema,
                "audiobooks-by.db"
            )
        ),
        siteApi = SiteApi(),
        algoliaSearchApi = AlgoliaSearchApi()
    )
}
