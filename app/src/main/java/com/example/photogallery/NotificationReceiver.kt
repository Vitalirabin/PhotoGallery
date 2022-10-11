package com.example.photogallery

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import org.chromium.base.Log

private const val TAG="NotificationReceiver"
class NotificationReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"Received result: $resultCode")
        if(resultCode!=Activity.RESULT_OK){

        }
        val requestCode = intent.getIntExtra(PollWorker.REQUEST_CODE,0)
        val notification:Notification=intent.getParcelableExtra(PollWorker.NOTIFICATION)!!

        val notificationManager=NotificationManagerCompat.from(context)
        notificationManager.notify(requestCode,notification)
    }
}