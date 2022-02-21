package com.augmentedera.kotlin.fcm

import android.util.Log
import com.augmentedera.kotlin.R
import com.quickblox.messages.services.fcm.QBFcmPushListenerService
import com.augmentedera.kotlin.ui.activity.SplashActivity
import com.augmentedera.kotlin.utils.ActivityLifecycle
import com.augmentedera.kotlin.utils.NotificationUtils

private const val NOTIFICATION_ID = 1

class PushListenerService : QBFcmPushListenerService() {
    private val TAG = PushListenerService::class.java.simpleName

    override fun sendPushMessage(data: MutableMap<Any?, Any?>?, from: String?, message: String?) {
        super.sendPushMessage(data, from, message)
        Log.v(TAG, "From: $from")
        Log.v(TAG, "Message: $message")

        if (ActivityLifecycle.isBackground()) {
            showNotification(message ?: " ")
        }
    }

    private fun showNotification(message: String) {
        NotificationUtils.showNotification(this, SplashActivity::class.java,
                getString(R.string.notification_title), message,
                R.drawable.ic_logo_vector, NOTIFICATION_ID)
    }
}