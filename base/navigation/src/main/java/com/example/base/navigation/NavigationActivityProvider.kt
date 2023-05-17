package com.example.base.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference

class NavigationActivityProvider(application: Application) {

    private var activityReference: WeakReference<NavigationActivity>? = null

    fun get(): NavigationActivity? = activityReference?.get()

    init {
        registerActivityCallbacks(application)
    }

    private fun registerActivityCallbacks(application: Application) {
        application.registerActivityLifecycleCallbacks(
            object : AppActivityLifecycleCallbacks() {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if (activity is NavigationActivity) {
                        activityReference = WeakReference(activity)
                    }
                }

                override fun onActivityDestroyed(activity: Activity) {
                    if (activity is NavigationActivity) {
                        activityReference = null
                    }
                }
            }
        )
    }
}