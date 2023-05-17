package com.example.base.navigation

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

abstract class AppActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
    override fun onActivityStarted(activity: Activity) {
        // do nothing
    }

    override fun onActivityResumed(activity: Activity) {
        // do nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // do nothing
    }

    override fun onActivityStopped(activity: Activity) {
        // do nothing
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // do nothing
    }
}