package com.alip.zy.tools;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alip.zy.view.activity.BaseImmersiveActivity;
import com.alip.zy.view.activity.HomeActivity;

public class NotificationActivity extends BaseImmersiveActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button btn1, btn2, btn3, btn4;

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }

    private void notificationTest() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /*
// The ID of the channel.
        String id = "my_channel_01";
// The user visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
// The user visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
// Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.setShowBadge(false);
        mNotificationManager.createNotificationChannel(mChannel);
        */



//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = 1;
        String CHANNEL_ID = "my_channel_01";
// Set a message count to associate with this notification in the long-press menu.
        int messageCount = 3;
// Create a notification and set a number to associate with it.

//        NotificationCompat.Builder builder = new NotificationCompat.Builder()

        Notification notification =
                new NotificationCompat.Builder(NotificationActivity.this)
                        .setContentTitle("New Messages")
                        .setContentText("You've received 3 new messages.")
                        .setSmallIcon(R.drawable.ic_send)
                        .setNumber(messageCount)
                        .build();
// Issue the notification.
        mNotificationManager.notify(notificationID, notification);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1){ //普通的Notification
            //1.通过系统服务获取NotificationManager对象
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //2.获取建造者对象，通过建造者对象来设置Notification的属性
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            //前三个必须设置
            builder.setContentTitle("提示:");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentText("ContentText");
            builder.setContentInfo("天气转凉，注意保暖");
            builder.setSubText("SubText");
            //设置大图标，如果大图标和小图标同时存在，则大图标在最左边，小图标在右下方
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), android.R.drawable.star_on));
            //收到通知，有三种用户提示方式：声音，震动，呼吸灯
            builder.setDefaults(Notification.DEFAULT_ALL);
            //第一次收到消息时的提示信息
            builder.setTicker("来消息了");

            /**
             * 3.设置跳转
             * 通过PendingIntent.getActivity获得一个PendingIntent对象,
             * PendingIntent对intent进行了封装，在达到某个要求后跳转。
             * 这里不能用intent进行跳转，因为intent必须在页面启动之后才能初始化，实现跳转，
             * 而通知没有页面，或者在某个组件没有初始化之前就执行了
             */
            Intent intent = new Intent(this,HomeActivity.class);
            PendingIntent activity = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(activity);
            //点击通知后进行跳转，会自动取消(这一步不要忘记)
            builder.setAutoCancel(true);

            //4.commit,确认刚才的设置，调用NotificationManager的notify方法后便可生效
            Notification notification = builder.build();
            notificationManager.notify(1,notification);
        }else if(v.getId() == R.id.btn2){ //显示大图的Notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            //这三个基础的设置必须有
            builder.setSmallIcon(android.R.drawable.ic_media_play);
            builder.setContentTitle("优酷");
            builder.setContentText("最新视频");

            //装饰者模式，BigPictureStyle：大图模式
            //BigPictureStyle对象初始化的时候要把builder传进去，在builder的基础上继续做高级的设置
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle(builder);
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),android.R.drawable.star_on));
            //bigPictureStyle必须要build
            bigPictureStyle.build();
            notificationManager.notify(2,builder.build());

        }else if(v.getId() == R.id.btn3){ //显示进度的Notification
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentText("正在下载");
            builder.setContentTitle("提示");
            int index = 0;
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                index += 200;
                //设置进度，第三个参数表示是否模糊，如果为true的话，就不会显示详细进度
                builder.setProgress(1000,index,false);

                Notification build = builder.build();
                notificationManager.notify(3, build);
            }
            //下载完成后，从通知栏移除指定id的通知
            notificationManager.cancel(3);
        }else if(v.getId() == R.id.btn4){ //下载网络图片
            Log.v("NotificationActivity", "not implemented yet");
//            //使用HttpUtils之前需要先导包
//            HttpUtils utils = new HttpUtils();
//            String url = "http://p3.so.qhmsg.com/t014a6628f309b37a93.jpg"; //图片地址
//            String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/1607.jpg"; //保存路径
//            //为什么使用抽象类而不是接口？
//            //抽象类可以有抽象方法，也可以有非抽象方法，实例化抽象类时对应他的非抽象方法可以选择性的去实现
//            utils.download(url, path, new RequestCallBack<File>() {
//                //当前我们要下载，需要知道进度，所以要选择一个非抽象的方法，即onLoading
//                //此方法在下载完成之前会一直执行
//                @Override
//                public void onLoading(long total, long current, boolean isUploading) {
//                    super.onLoading(total, current, isUploading);
//                    //拿到NotificationManager对象
//                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                    //拿到Builder对象
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(HomeActivity.this);
//
//                    //设置Builder的属性
//                    builder.setSmallIcon(android.R.drawable.stat_sys_download);
//                    builder.setContentTitle("提示");
//                    //把下载的进度显示在通知栏上
//                    builder.setContentText((current/(float)total)*100 + "%");
//                    builder.setProgress((int)total,(int)current,false);
//
//                    Notification build = builder.build();
//                    notificationManager.notify(4,build);
//                }
//
//                //下载成功后，注意实现用户点击后跳转到查看页面
//                @Override
//                public void onSuccess(ResponseInfo<File> responseInfo) {
//                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                    notificationManager.cancel(4);
//                    //下载成功后，提示用户“点击查看”，点击后跳转到另外一个页面
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(HomeActivity.this);
//                    builder.setSmallIcon(android.R.drawable.stat_sys_download);
//                    builder.setContentTitle("提示");
//                    builder.setContentText("点击查看");
//                    Intent intent = new Intent(HomeActivity.this,Main2Activity.class);
//                    PendingIntent pendingIntent = PendingIntent.getActivity(HomeActivity.this,6,intent,0);
//                    builder.setContentIntent(pendingIntent);
//                    builder.setAutoCancel(true);
//
//                    Notification build = builder.build();
//                    notificationManager.notify(1,build);
//                }
//
//                //下载失败，要打印出错误信息
//                @Override
//                public void onFailure(HttpException error, String msg) {
//                    Log.e("error", msg);
//                }
//            });
        }
    }
}
