package com.example.webbiestest.Worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.webbiestest.R;

/**
 * Worker classes are only responsible for initiation of work
 * There are many types of worker are available for doing different type of work.
 * 1. single .. on which task initiated only one time.
 * 2. periodic .. on which task initiated so many on given interval of time. :)
 */
public class MyWork extends Worker {
   public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
      super(context, workerParams);
   }

   @NonNull
   @Override

   public Result doWork() {
      getNotification("Hii Dheeraj...", "Notification generated... enjoy it..");
      return Result.success();
   }

   /**
    * trigger notification for testing
    *
    * @param task ..notification title...
    * @param desc ..notification description...
    */
   public void getNotification(String task, String desc) {
      NotificationManager manager =
            (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         NotificationChannel channel = new NotificationChannel("TestId", "Test", NotificationManager.IMPORTANCE_DEFAULT);
         manager.createNotificationChannel(channel);
      }

      NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),
            "TestId")
            .setContentTitle(task)                   //set the title of notification.
            .setContentText(desc)                   //set description of notification.
            .setSmallIcon(R.mipmap.ic_launcher);   //set icon for notification.

      manager.notify(1, builder.build());
   }


}
