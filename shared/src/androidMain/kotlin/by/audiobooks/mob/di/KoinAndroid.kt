package by.audiobooks.mob.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.data.RepositoryImpl
import by.audiobooks.mob.data.db.AudiobooksByDB
import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.AlgoliaSearchApi
import by.audiobooks.mob.data.network.SiteApi
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin() = initKoin {}.koin

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule(), platformModule)
    }

fun commonModule() = module {
    single { SiteApi() } // Network
    single { AlgoliaSearchApi() } // Search
    single { DatabaseHelper(sqlDriver = get()) } // DB
    single<Repository> { RepositoryImpl(dbHelper = get(), siteApi = get(), algoliaSearchApi = get()) } // Repository
}

val platformModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(AudiobooksByDB.Schema, get(), "audiobooks-by.db")
    }
}
