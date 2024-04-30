package by.audiobooks.mob.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import by.audiobooks.mob.data.db.AudiobooksByDB
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module


actual val platformModule = module {
    single<SqlDriver> {
        NativeSqliteDriver(AudiobooksByDB.Schema, "audiobooks-by.db")
    }
}

@kotlinx.cinterop.BetaInteropApi
fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, null, null)
}

@kotlinx.cinterop.BetaInteropApi
fun Koin.get(objCClass: ObjCClass, qualifier: Qualifier?, parameter: Any): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz, qualifier) { parametersOf(parameter) }
}
