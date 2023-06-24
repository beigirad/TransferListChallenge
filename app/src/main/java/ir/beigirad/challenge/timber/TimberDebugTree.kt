package ir.beigirad.challenge.timber

import timber.log.Timber

/**
 * Created by Farhad Beigirad on 6/25/23.
 */
class TimberDebugTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, "Timber $tag", message, t)
    }
}