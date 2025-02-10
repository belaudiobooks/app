package by.audiobooks.mob.di

import by.audiobooks.mob.presentation.catalog.CatalogViewModel
import by.audiobooks.mob.presentation.catalog.CategoryViewModel
import by.audiobooks.mob.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::CatalogViewModel)
    viewModelOf(::CategoryViewModel)
}