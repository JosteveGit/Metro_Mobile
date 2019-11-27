package com.metro.metromobile.utils.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;



import java.util.Random;

public class BlackKnightsMessagingService {

    private static final String ADMIN_CHANNEL_ID ="admin_channel";

//    @Override
//    public void onNewToken(String token) {
//        super.onNewToken(token);
//
//        if (SessionManager.isUserLogin()) {
//            TokenManager.storeToken(token);
//            TokenManager.setServerReceivedAccessToken(false);
//        }
//    }
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//
//        if (!SessionManager.isUserLogin()) {
//            return;
//        }
//
//        NotificationManager notificationManager = (NotificationManager)
//                getSystemService(Context.NOTIFICATION_SERVICE);
//
//        //Setting up Notification channels for android O and above
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            setupChannels(notificationManager);
//        }
//
//        NotificationCompat.Builder notificationBuilder = getBuilder(remoteMessage);
//        int notificationId = new Random().nextInt(60000);
//
//        if (notificationManager != null) {
//            notificationManager.notify(notificationId, notificationBuilder.build());
//        }
//    }
//
//    private NotificationCompat.Builder getBuilder(RemoteMessage remoteMessage) {
//        PendingIntent pendingIntent = getPendingIntent();
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        return new NotificationCompat.Builder(this,
//                getString(R.string.default_notification_channel_id))
//                .setSmallIcon(R.drawable.ic_black_knights_notification)
//                .setContentTitle(remoteMessage.getData().get("title"))
//                .setContentText(remoteMessage.getData().get("message"))
//                .setAutoCancel(true)
//                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                .setSound(defaultSoundUri);
//    }
//
//    private PendingIntent getPendingIntent() {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        return PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void setupChannels(NotificationManager notificationManager){
//        CharSequence adminChannelName = getString(R.string.notifications_admin_channel_name);
//        String adminChannelDescription = getString(R.string.notifications_admin_channel_description);
//
//        NotificationChannel adminChannel;
//        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName,
//                NotificationManager.IMPORTANCE_DEFAULT);
//        adminChannel.setDescription(adminChannelDescription);
//        adminChannel.enableLights(true);
//        adminChannel.setLightColor(ContextCompat.getColor(this, R.color.colorAccent));
//        adminChannel.enableVibration(true);
//        adminChannel.setVibrationPattern(new long[]{0,1000,500,1000});
//
//        if (notificationManager != null) {
//            notificationManager.createNotificationChannel(adminChannel);
//        }
//    }
}