package dohun.kim.kinda

import android.app.Application
import dohun.kim.kinda.count.countModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CountApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CountApplication)
            modules(
                countModule
            )
        }
    }
}