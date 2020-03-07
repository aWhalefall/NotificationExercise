package com.zenglb.framework.updateinstaller;

import android.Manifest;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.zenglb.downloadinstaller.DownloadInstaller;
import com.zenglb.downloadinstaller.DownloadProgressCallBack;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * app 内部升级
 */
public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private String apkDownLoadUrlCOPY = "http://pro-app-qn.fir.im/a129a8163c341efa174432c0f9dcb9e81423a0d3.apk?attname=module_news-release.apk_1.1.apk&e=1561691950&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:NNyBMuVUdUSlHQKnWEVt1UN31IU=";

    //URL 下载有时间效益
    private String apkDownLoadUrl = "http://img.4009515151.com//2019/06/26/15/7ace8dd995-comvankewyguide_3.8.6_0e3f3e28-39b0-543b-a847-6c70999d3b68.apk";

    private String apkDownLoadUrl2 = "https://ali-fir-pro-binary.fir.im/ea7df71390403635b5f744d82d28c13fc865c325.apk?auth_key=1557455233-0-0-2b4c71ac353961eab7fa2a65ec641bb4";
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationDemo.getInstance().createNotificationChannel(this);
        /**
         * 测试升级安装
         */
        findViewById(R.id.update).setOnClickListener(v -> showUpdateDialog("1.升级后App 会自动攒钱\n2.还可以做白日梦", true, apkDownLoadUrl));

        /**
         * 测试升级安装2,无效下载链接
         */
        findViewById(R.id.update2).setOnClickListener(v -> showUpdateDialog("1.升级后App 会自动攒钱\n2.还可以做白日梦", false, apkDownLoadUrl2));

        methodRequiresPermission();
    }


    /**
     * 显示下载的对话框,是否要强制的升级还是正常的升级
     *
     * @param UpdateMsg     升级信息
     * @param isForceUpdate 是否是强制升级
     * @param downloadUrl   APK 下载URL
     */
    private void showUpdateDialog(String UpdateMsg, boolean isForceUpdate, String downloadUrl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = LayoutInflater.from(this);
        View updateView = inflater.inflate(R.layout.update_layout, null);
        NumberProgressBar progressBar = updateView.findViewById(R.id.tips_progress);
        TextView updateMsg = updateView.findViewById(R.id.update_mess_txt);
        updateMsg.setText(UpdateMsg);
        builder.setTitle("发现新版本");
        String negativeBtnStr = "以后再说";

        if (isForceUpdate) {
            builder.setTitle("强制升级");
            negativeBtnStr = "退出应用";
        }

        builder.setView(updateView);
        builder.setNegativeButton(negativeBtnStr, null);
        builder.setPositiveButton(R.string.apk_update_yes, null);

        AlertDialog downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        downloadDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            if (isForceUpdate) {
                progressBar.setVisibility(View.VISIBLE);

                //新加 isForceGrantUnKnowSource 参数

                //如果是企业内部应用升级，肯定是要这个权限，其他情况不要太流氓，TOAST 提示
                //这里演示要强制安装
                new DownloadInstaller(this, downloadUrl, true, new DownloadProgressCallBack() {
                    @Override
                    public void downloadProgress(int progress) {
                        runOnUiThread(() -> progressBar.setProgress(progress));
                        if (progress == 100) {
                            downloadDialog.dismiss();
                        }
                    }

                    @Override
                    public void downloadException(Exception e) {
                    }

                    @Override
                    public void onInstallStart() {
                        downloadDialog.dismiss();
                    }
                }).start();

                //升级按钮变灰色
                downloadDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GRAY);

            } else {
                new DownloadInstaller(this, downloadUrl).start();
                downloadDialog.dismiss();
            }
        });

        downloadDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> {
            if (isForceUpdate) {
                MainActivity.this.finish();
            } else {
                downloadDialog.dismiss();
            }
        });

    }


    /**
     * 请求权限,创建目录的权限
     */
    private void methodRequiresPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(MainActivity.this, "App 升级需要储存权限", 10086, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {
        // Some permissions have been granted
        // ...
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> list) {
        // Some permissions have been denied
        // ...
    }

    DownloadInstaller installer = null;

    public void testNotification(View view) {

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
        NotificationCompat.Builder builder = NotificationDemo.getInstance().creatMessageNotification(MainActivity.this);
        notificationManager.notify(NotificationDemo.CHANNEL_ID_INT, builder.build());
    }
}
