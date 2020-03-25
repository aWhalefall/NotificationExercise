package com.zenglb.framework.updateinstaller;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;


import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import java.util.Random;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class NotificationDemo {

    private static NotificationDemo notificationDemo;

    private NotificationDemo() {
    }

    public static NotificationDemo getInstance() {
        if (notificationDemo == null)
            notificationDemo = new NotificationDemo();
        return notificationDemo;
    }

    public static final String CHANNEL_ID_STRING = "10000";
    public static final int CHANNEL_ID_INT = 10000;

    /**
     * 创建基础通知
     *
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatBasicNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("基础通知内容标题")
                .setContentText("基础通知内容")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder;
    }

    /**
     * 创建PendingIntent 跳转到指定的Activity
     *
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatPendingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, new Random().nextInt(100), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("跳转到指定的Activity")
                .setContentText("跳转到指定的Activity")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder;
    }

    /**
     * 通知上创建Action
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatPendingIntentAction(Context context) {
        Intent snoozeIntent = new Intent(context, MyBroadcastReceiver.class);
        snoozeIntent.setAction("ACTION_SNOOZE");
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("pending Action")
                .setContentText("pending Action")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(snoozePendingIntent)
                .addAction(R.drawable.ic_launcher_background, "重试",
                        snoozePendingIntent);

        return builder;
    }

    /**
     * 带回复的action
     * https://support.google.com/googleplay/android-developer#topic=3450769 demo
     * @param context
     * @return
     */
    public NotificationCompat.Builder AddReplayAction(Context context) {

        String KEY_TEXT_REPLY = "key_text_reply";
        String replyLabel = "回复";
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                .setLabel(replyLabel)
                .build();

        int conversationID=new Random().nextInt(100);

        Intent snoozeIntent = new Intent(context, MyBroadcastReceiver.class);
        snoozeIntent.putExtra("conversationId",conversationID);
        snoozeIntent.setAction("ACTION_SNOOZE");
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);

        PendingIntent replyPendingIntent =
                PendingIntent.getBroadcast(context,
                        conversationID,
                        snoozeIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // Create the reply action and add the remote input.
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background,
                        "回复", replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("基础通知内容标题")
                .setContentText("基础通知内容")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).addAction(action);

        return builder;
    }

    /**
     * 多行文本
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatBasicBigTextNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("基础通知内容标题")
                .setContentText("基础通知内容")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("For more information about other large notification styles, including how to add an image and media playback controls, see Create a Notification with Expandable Detail..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        return builder;
    }

    /**
     * 创建带进度条的notification
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatProgressbarNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 15;
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);

        return builder;
    }

    /**
     * Set a system-wide category
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatSystemWidget(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        return builder;
    }


    /**
     * 重要通知
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatImportMessage(Context context){

        Intent fullScreenIntent = new Intent(context, LocalBroadCaseExerciseActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setFullScreenIntent(fullScreenPendingIntent, true);
        return builder;
    }

    /**
     * 通知聊天消息通知
     * @param context
     * @return
     */
    public NotificationCompat.Builder creatMessageNotification(Context context){
        NotificationCompat.Builder  builder = new NotificationCompat.Builder(context, CHANNEL_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setStyle(new NotificationCompat.MessagingStyle("Me")
                        .setConversationTitle("Team lunch")
                        .addMessage("Hi", System.currentTimeMillis(), new Person.Builder().setName("yang").build()) // Pass in null for user.
                        .addMessage("What's up?", System.currentTimeMillis(), new Person.Builder().setName("yang2").build())
                        .addMessage("Not much", System.currentTimeMillis(), new Person.Builder().setName("yang").build())
                        .addMessage("How about lunch?", System.currentTimeMillis(), new Person.Builder().setName("yang2").build()));
        return builder;
    }




    /**
     * 创建通知渠道
     * @param context
     * @return
     */
    public NotificationManager createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        NotificationManager notificationManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            notificationManager = context.getSystemService(NotificationManager.class);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel名称";
            String description = "这里用来描述channel 名称";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NotificationDemo.CHANNEL_ID_STRING, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
        return  notificationManager;
    }


}
