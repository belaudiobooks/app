package by.audiobooks.mob.di

import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.data.RepositoryImpl
import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.SiteApi
import by.audiobooks.mob.presentation.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
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
    single { DatabaseHelper(sqlDriver = get()) } // DB
    single<Repository> { RepositoryImpl(dbHelper = get(), siteApi = get()) } // Repository
    // ViewModels:
    factory { HomeViewModel(repository = get()) }
}

expect val platformModule: Module