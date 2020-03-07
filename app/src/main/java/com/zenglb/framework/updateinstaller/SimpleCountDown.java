package com.zenglb.framework.updateinstaller;

import android.app.NotificationManager;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;

public class SimpleCountDown extends CountDownTimer {

    private final NotificationManager manager;
    private NotificationCompat.Builder builder;


    public SimpleCountDown(int i, int i1, NotificationCompat.Builder build, NotificationManager notificationManager) {
        super(i, i1);
        this.builder=build;
        this.manager=notificationManager;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        builder.setProgress(100, 100-(int) (millisUntilFinished/1000),false);
        manager.notify(NotificationDemo.CHANNEL_ID_INT,builder.build());
    }

    @Override
    public void onFinish() {

    }
}
