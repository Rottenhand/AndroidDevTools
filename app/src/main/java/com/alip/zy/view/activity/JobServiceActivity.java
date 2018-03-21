package com.alip.zy.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by ZY on 2018/1/4.
 */
public class JobServiceActivity extends BaseImmersiveActivity {

    public static final int MY_BACKGROUND_JOB = 0;

    private static final String TAG = "JobService";
    private static final int DEFAULT_INTERVAL_HOUR = 6;
    private static final String CONFIG_ALLOW_CHARGING_JOBSERVICE = "allow_charging_jobservice";
    private static final String CONFIG_INTERVAL_HOUR = "charging_jobservice_interval_hour";
    private static final String SP_NAME = "charging_job_scheduler";
    private static final String SP_KEY_LAST_SCHEDULE_TIME = "last_schedule_time";
    private boolean scheduling;
    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



//    public static void scheduleJob(Context context) {
//        JobScheduler js =
//                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo job = new JobInfo.Builder(
//                MY_BACKGROUND_JOB,
//                new ComponentName(context, MyJobService.class))
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setRequiresCharging(true)
//                .build();
//        js.schedule(job);
//    }
//
//
//    public static void scheduleJob(Context context) {
//        JobScheduler js =
//                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
//        JobInfo.Builder builder = new JobInfo.Builder(
//                MY_BACKGROUND_JOB,
//                new ComponentName(context, MediaContentJob.class));
//        builder.addTriggerContentUri(
//                new JobInfo.TriggerContentUri(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        JobInfo.TriggerContentUri.FLAG_NOTIFY_FOR_DESCENDANTS));
//        js.schedule(builder.build());
//    }

}
