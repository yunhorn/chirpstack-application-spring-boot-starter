package com.yunhorn.core.chirpstack.task;

import com.yunhorn.core.chirpstack.config.DeviceSyncConfig;
import com.yunhorn.core.chirpstack.config.UserInfo;
import com.yunhorn.core.chirpstack.dto.ApplicationSyncReq;
import com.yunhorn.core.chirpstack.dto.DeviceSyncReq;
import com.yunhorn.core.chirpstack.sync.SyncService;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author ljm
 * @date 2021/12/31 14:23
 */
@Component
@Slf4j
public class ChirpStackSyncTask extends ChirpStackBaseTask {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private SyncService syncService;

    @Autowired
    private DeviceSyncConfig deviceSyncConfig;

    public static ScheduledExecutorService syncScheduledThreadPool = new ScheduledThreadPoolExecutor(1);

    public void syncApplication(){
        if (!taskSwitch("syncApplication")){
            return;
        }
        syncScheduledThreadPool.schedule(()->{
            ApplicationSyncReq applicationSyncReq = new ApplicationSyncReq();
            try {
                BeanUtils.copyProperties(applicationSyncReq,userInfo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("SyncApplication copyProperties error|{}|{}", JSONUtils.beanToJson(applicationSyncReq),JSONUtils.beanToJson(userInfo));
                return;
            }
            syncService.syncApplication(applicationSyncReq);
        },getDuration(),getDurationUnit());
    }

    public void syncDevice(){
        if (!taskSwitch("syncDevice")){
            return;
        }
        syncScheduledThreadPool.schedule(()->{
            DeviceSyncReq deviceSyncReq = new DeviceSyncReq();
            try {
                BeanUtils.copyProperties(deviceSyncReq,userInfo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("SyncDevice copyProperties error|{}|{}", JSONUtils.beanToJson(deviceSyncReq),JSONUtils.beanToJson(userInfo));
                return;
            }
            deviceSyncReq.setApplicationNames(deviceSyncConfig.getApplicationNames());
            syncService.syncDevice(deviceSyncReq);
        },getDuration(),getDurationUnit());
    }

}
