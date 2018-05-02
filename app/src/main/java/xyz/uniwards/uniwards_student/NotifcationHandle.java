package xyz.uniwards.uniwards_student;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import xyz.uniwards.uniwards_student.MainScreens.DashboardActivity;

/**
 * Created by Umayr on 4/30/2018.
 */

public class NotifcationHandle {
    public static void MakeNotifcation(Context context, String message, String title, Integer icon, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

       // String id = "notiChannel_" + title.replace("", "_");
        //CharSequence name = title.replace("", "_");
        //NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
        //notificationManager.createNotificationChannel(channel);

        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        Notification notifcation  = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                //.setChannelId(id)
                .build();
                //.addAction(R.drawable.icon, "More", pIntent)
                //.addAction(R.drawable.icon, "And more", pIntent).build();

        notificationManager.notify(1, notifcation);
    }

    public static void MakeActionNotifcation(Context context, String message, String title, Integer icon, Intent intent, Notification.Action action) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);
        Notification notifcation  = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(icon)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(action).build();

        notificationManager.notify(0, notifcation);
    }
}
