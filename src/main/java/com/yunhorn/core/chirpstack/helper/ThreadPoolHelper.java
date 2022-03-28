package com.yunhorn.core.chirpstack.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author ljm
 * @date 2022/3/28 12:08
 */
@Slf4j
public class ThreadPoolHelper {
    public static ScheduledExecutorService syncScheduledThreadPool = new ScheduledThreadPoolExecutor(1);
    public static void closeThreadPool(){
        log.info("Begin shutdown the ThreadPool");
        syncScheduledThreadPool.shutdown();
        if (syncScheduledThreadPool.isShutdown()){
            log.info("Shutdown the ThreadPool success");
        }else {
            log.error("Shutdown the ThreadPool fail");
        }
    }
}
