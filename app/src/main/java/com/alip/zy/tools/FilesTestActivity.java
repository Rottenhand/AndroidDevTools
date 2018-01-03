package com.alip.zy.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alip.zy.view.activity.BaseImmersiveActivity;

public class FilesTestActivity extends BaseImmersiveActivity {

    /**
     *
     *
     Environment.getDataDirectory() = /data
     Environment.getDownloadCacheDirectory() = /cache
     Environment.getExternalStorageDirectory() = /mnt/sdcard
     Environment.getExternalStoragePublicDirectory(“test”) = /mnt/sdcard/test
     Environment.getRootDirectory() = /system
     getPackageCodePath() = /data/app/com.my.app-1.apk
     getPackageResourcePath() = /data/app/com.my.app-1.apk
     getCacheDir() = /data/data/com.my.app/cache
     getDatabasePath(“test”) = /data/data/com.my.app/databases/test
     getDir(“test”, Context.MODE_PRIVATE) = /data/data/com.my.app/app_test
     getExternalCacheDir() = /mnt/sdcard/Android/data/com.my.app/cache
     getExternalFilesDir(“test”) = /mnt/sdcard/Android/data/com.my.app/files/test
     getExternalFilesDir(null) = /mnt/sdcard/Android/data/com.my.app/files
     getFilesDir() = /data/data/com.my.app/files
     *
     */

    private Button fileDir, exFileDir, cacheDir, exCacheDir;
    private TextView showDir;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files_test);
        showDir = (TextView) findViewById(R.id.show_file_dir);
    }

    public void showFileDir(View view) {
        path = this.getFilesDir().getAbsolutePath();
        showDir.setText(path);
    }

    public void showExternalFileDir(View view) {
        path = this.getExternalFilesDir(null).getAbsolutePath();
        showDir.setText(path);
    }

    public void showCacheDir(View view) {
        path = this.getCacheDir().getAbsolutePath();
        showDir.setText(path);
    }

    public void showExternalCacheDir(View view) {
        path = this.getExternalCacheDir().getAbsolutePath();
        showDir.setText(path);



//        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        int notificationID = 1;
//        String CHANNEL_ID = "my_channel_01";
//// Set a message count to associate with this notification in the long-press menu.
//        int messageCount = 3;
//// Create a notification and set a number to associate with it.
//        NotificationCompat notification =
//                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
//                        .setContentTitle("New Messages")
//                        .setContentText("You've received 3 new messages.")
//                        .setSmallIcon(R.drawable.ic_notify_status)
//                        .setNumber(messageCount)
//                        .build();
//// Issue the notification.
//        mNotificationManager.notify(notificationID, notification);
    }
}
