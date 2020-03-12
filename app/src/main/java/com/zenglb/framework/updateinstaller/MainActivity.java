package com.zenglb.framework.updateinstaller;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.notification.demo.NotificationiActivity;

import java.util.List;


/**
 * app 内部升级
 */
public class MainActivity extends AppCompatActivity  {



    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationDemo.getInstance().createNotificationChannel(this);
    }





    /**
     * 请求权限,创建目录的权限
     */
    private void methodRequiresPermission() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
    }



    public void testNotification(View view) {
        startActivity(new Intent(this, NotificationiActivity.class));

//        boolean judge=new Random().nextBoolean();
//        if(judge){
//            installer=new DownloadInstaller(this);
//        }else {
//            installer=new DownloadInstaller(this,0);
//        }
//        installer.notifyError(""+new Random().nextInt(10),new Random().nextInt(100));




        //notificationManager.notify(NotificationDemo.CHANNEL_ID_INT,NotificationDemo.getInstance().creatBasicNotification(this).build());
        //多行文本Notification
        // notificationManager.notify(NotificationDemo.CHANNEL_ID_INT,NotificationDemo.getInstance().creatBasicBigTextNotification(this).build());
        //跳转到指定的activity
        //  notificationManager.notify(NotificationDemo.CHANNEL_ID_INT + new Random().nextInt(3), NotificationDemo.getInstance().creatPendingIntent(this).build());
        //注册
        //notificationManager.notify(NotificationDemo.CHANNEL_ID_INT,NotificationDemo.getInstance().creatPendingIntentAction(this).build());
        //增加回复Action
//        notificationManager.notify(NotificationDemo.CHANNEL_ID_INT,NotificationDemo.getInstance().AddReplayAction(this).build());
        //增加带进度条notifacation
//        NotificationCompat.Builder builder = NotificationDemo.getInstance().creatProgressbarNotification(this);
//        notificationManager.notify(NotificationDemo.CHANNEL_ID_INT, builder.build());
//        new SimpleCountDown(60 * 1000, 1000, builder, notificationManager).start();
        //免打扰
//        NotificationCompat.Builder builder = NotificationDemo.getInstance().creatSystemWidget(this);
//        notificationManager.notify(NotificationDemo.CHANNEL_ID_INT, builder.build());
        //重要通知,锁屏通知
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                NotificationCompat.Builder builder = NotificationDemo.getInstance().creatImportMessage(MainActivity.this);
//                notificationManager.notify(NotificationDemo.CHANNEL_ID_INT, builder.build());
//            }
//        },2500);
        //设置通知的可见性，隐私，公开，私密。可以设置替代通知（）setPublicVersion

        //更新通知

        //移除通知

        //聊天通知
//        NotificationCompat.Builder builder = NotificationDemo.getInstance().creatMessageNotification(MainActivity.this);
//        notificationManager.notify(NotificationDemo.CHANNEL_ID_INT, builder.build());
    }
}
