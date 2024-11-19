import app.cash.sqldelight.driver.native.NativeSqliteDriver
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.data.RepositoryImpl
import by.audiobooks.mob.data.db.AudiobooksByDB
import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.AlgoliaSearchApi
import by.audiobooks.mob.data.network.SiteApi
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun getRepository(): Repository {
    return RepositoryImpl(
        dbHelper = DatabaseHelper(NativeSqliteDriver(AudiobooksByDB.Schema, "audiobooks-by.db")),
        siteApi = SiteApi(),
        algoliaSearchApi = AlgoliaSearchApi()
    )
}
