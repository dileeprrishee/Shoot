package com.example.Shoot.FireBase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.Shoot.R;
import com.example.Shoot.UI.Activities.PlayHistory;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseClass extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if (remoteMessage.getData().size() > 0) {
            String type = remoteMessage.getData().get("key");
            String message = remoteMessage.getData().get("message");
            String text = remoteMessage.getNotification().getBody();
            String click_action = remoteMessage.getNotification().getClickAction() + "";
            createNotification(remoteMessage, defaultSoundUri, type);
        } else {
            createNotification(remoteMessage, defaultSoundUri, "");
        }
    }

    private void createNotification(RemoteMessage remoteMessage, Uri defauldsound, String intentValue) {

        Intent intent = new Intent(this, PlayHistory.class);
        intent.putExtra("key", intentValue);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String channelId = "Default";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.shootlogoxhdpi)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(defauldsound)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        ;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        manager.notify(0, builder.build());

    }
}
