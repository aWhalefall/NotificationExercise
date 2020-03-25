package com.zenglb.framework.updateinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
/**
* author= 杨卫超
* creatTime=2020/3/25
* descript= 本地广播demo。
*/
public class LocalBroadCaseExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //注册
        LocalBroadcastManager.getInstance(this).registerReceiver(new DemoBroadcastReceiver(), new IntentFilter(LOCAL_ACTION));
    }

    public void testNotification(View view) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("123"));

        //发送广播
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(LOCAL_ACTION));
    }

    private static final String LOCAL_ACTION = "BROADCAST_ACTION";


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        LocalBroadcastManager.getInstance(this).unregisterReceiver(new DemoBroadcastReceiver());
    }

    final class DemoBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && LOCAL_ACTION.equals(intent.getAction())) {
                Log.v("zxy", "收到通知");
            }
        }
    }
}


