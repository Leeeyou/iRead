package com.leeeyou.manager.timber

import android.support.annotation.Nullable
import android.util.Log
import android.util.Log.INFO
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        FakeCrashLibrary.log(priority, tag, message)

        if (t != null) {
            if (priority == Log.ERROR) {
                FakeCrashLibrary.logError(t)
            } else if (priority == Log.WARN) {
                FakeCrashLibrary.logWarning(t)
            }
        }
    }

    fun isLoggable(priority: Int, @Nullable tag: String): Boolean {
        return priority >= INFO
    }
}