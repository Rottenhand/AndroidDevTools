package com.alip.zy.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {

    /*
     * Gets the number of available cores
     * (not always the same as the maximum number of cores)
     */
    private static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 60;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private static ThreadPoolExecutor executor;
    private static ScheduledExecutorService scheduledExecutor;


    public static void runTask(Runnable task) {
        buildThreadPoolExecutor();
        executor.execute(task);
    }

    public static ScheduledFuture<?> scedualTask(Runnable task, long delay) {
        buildScheduledExecutor();
        return scheduledExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    private static void buildThreadPoolExecutor() {
//            // create thread pool manuel
//        if (executor == null) {
//            // Instantiates the queue of Runnables as a LinkedBlockingQueue
//            executor = new ThreadPoolExecutor(
//                    NUMBER_OF_CORES,       // Initial pool size
//                    NUMBER_OF_CORES,       // Max pool size
//                    KEEP_ALIVE_TIME,
//                    KEEP_ALIVE_TIME_UNIT,
//                    new LinkedBlockingQueue<Runnable>());
//        }
        // create thread pool by Executors api
        if (executor == null) {
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUMBER_OF_CORES);
        }
    }

    private static void buildScheduledExecutor() {
        if (scheduledExecutor == null) {
            scheduledExecutor = Executors.newScheduledThreadPool(NUMBER_OF_CORES);
        }
    }
}
