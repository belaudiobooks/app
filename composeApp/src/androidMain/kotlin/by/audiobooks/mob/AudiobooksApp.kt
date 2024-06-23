package by.audiobooks.mob

import android.app.Application
import by.audiobooks.mob.di.appModule
import by.audiobooks.mob.di.initKoin
import by.audiobooks.mob.sync.SyncWorker
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AudiobooksApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())
        initKoin {
            androidContext(this@AudiobooksApp)
            androidLogger()
            modules(appModule)
        }
        syncData()
    }

    private fun syncData() {
        SyncWorker.enqueue(this)
    }

}