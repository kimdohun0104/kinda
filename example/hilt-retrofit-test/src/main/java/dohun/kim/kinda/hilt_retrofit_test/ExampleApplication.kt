package dohun.kim.kinda.hilt_retrofit_test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dohun.kim.kinda.kinda_core.logging.KindaLoggerSetting

@HiltAndroidApp
class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KindaLoggerSetting.enableLogging()
    }
}