package com.widecare.indiaweb.myapplication.notification;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.widecare.indiaweb.myapplication.R
import com.widecare.indiaweb.myapplication.SplashActivity

class FirebaseMessagingService : FirebaseMessagingService() {
var TAG="FirebaseMessagingService "
    var body: String? = null
    var logoImg: String? = null
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG+" ", "onMessageReceived="+remoteMessage.data.toString())
//   Log.d("sdadsadasdas222",remoteMessage.notification!!.body.toString())
   //Log.d("sdadsadasdas222title",remoteMessage.notification!!.title.toString())
//  Log.d("sdadsadasdas222mesget",remoteMessage.messageType.toString())
        //Log.d("sdadsadasdas222messagetype",remoteMessage)

        try{
   Log.d("tryBlock first","remoteMessage.notification!!.body.toString()")
            sendNotification(remoteMessage.notification!!.title.toString(),remoteMessage.notification!!.body.toString())
        }
        catch (exception :Exception)
        {
            Log.d("catchBlock first",""+exception.toString())
         }

        try{
           Log.d(TAG+" tryBlock second","try" /*remoteMessage.data.get("title")*/)
            sendNotification(/*remoteMessage.data.get("title").toString()*/ "ttktitle","body"/*remoteMessage.data.get("body").toString()*/)
        }
        catch (exception :Exception)
        {
            Log.d("catchBlock second",""+exception.toString())
       }

        //    .setContentTitle(message.notification!!.title.toString())
        // .setContentText(message.notification!!.body.toString())


    }

     /*override fun onNewToken(strToken: String) {
        super.onNewToken(strToken)
        storeNewToken(strToken)

    }*/


    private fun storeNewToken(strToken: String) {
     //   CSPreferences.putString(context, "session_id", "")
        val pref = applicationContext.getSharedPreferences("CS_PREF", 0)
        val editor = pref.edit()
        editor.putString("regId", strToken)
        editor.commit()
    }

    fun sendNotification(title:String,bodyTxt:String) {

        var intent: Intent? = null
         intent = Intent(this, SplashActivity::class.java)
        // add this:
        // intent.action = "showmessage";

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my_channel"
            val descriptionText = "Privexec"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                getString(R.string.default_notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        var notificationBuilder =
            NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.drawable.logo_icon)//R.mipmap.app_logo
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo_icon))
                //.setColor(getResources().getColor(android.R.color.holo_red_dark))
                 .setContentTitle(title)
                 .setContentText(bodyTxt)
                 .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)

                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(this)) {
             notify(0, notificationBuilder.build())
        }

    }
}