import android.content.Context
import android.os.Build
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import by.audiobooks.mob.data.Repository
import by.audiobooks.mob.data.RepositoryImpl
import by.audiobooks.mob.data.db.AudiobooksByDB
import by.audiobooks.mob.data.db.DatabaseHelper
import by.audiobooks.mob.data.network.SiteApi

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

fun getRepository(context: Context): Repository {
    return RepositoryImpl(
        dbHelper = DatabaseHelper(AndroidSqliteDriver(AudiobooksByDB.Schema, context, "audiobooks-by.db")),
        siteApi = SiteApi()
    )
}
