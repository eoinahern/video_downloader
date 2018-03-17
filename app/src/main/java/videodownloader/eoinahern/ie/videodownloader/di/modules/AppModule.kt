package videodownloader.eoinahern.ie.videodownloader.di.modules


import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.preference.PreferenceManager
import android.support.v4.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import videodownloader.eoinahern.ie.videodownloader.MyApp
import videodownloader.eoinahern.ie.videodownloader.data.Parser.ParserFactory
import videodownloader.eoinahern.ie.videodownloader.ui.util.DownloadNotificationHelper
import java.io.File
import javax.inject.Singleton

@Module
class AppModule(var myApp: MyApp) {

	@Singleton
	@Provides
	fun getContext(): Context = myApp


	@Provides
	@Singleton
	fun getSharedPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)


	@Provides
	@Singleton
	fun getNotificationManager(context: Context): NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

	@Provides
	@Singleton
	fun getClient(): OkHttpClient = OkHttpClient()


	@Provides
	@Singleton
	fun getDownloadsDir(): File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)


	@Provides
	@Singleton
	fun getDownloadNotifHelper(context: Context, notifManager: NotificationManager):
			DownloadNotificationHelper = DownloadNotificationHelper(context, notifManager)

	@Provides
	@Singleton
	fun getParserFactory(): ParserFactory = ParserFactory
}
