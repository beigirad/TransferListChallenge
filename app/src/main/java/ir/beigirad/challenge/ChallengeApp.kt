package ir.beigirad.challenge

import android.app.Application
import com.wada811.viewbindingktx.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import ir.beigirad.challenge.timber.TimberDebugTree
import timber.log.Timber

/**
 * Created by Farhad Beigirad on 6/23/23.
 */
@HiltAndroidApp
class ChallengeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(TimberDebugTree())
    }
}