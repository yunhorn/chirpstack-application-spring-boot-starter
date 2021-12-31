package com.yunhorn.core.chirpstack.task;

import com.yunhorn.core.chirpstack.config.UserInfo;
import com.yunhorn.core.chirpstack.dto.SyncReq;
import com.yunhorn.core.chirpstack.helper.GlobalHelper;
import com.yunhorn.core.chirpstack.sync.SyncService;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    public static ScheduledExecutorService syncScheduledThreadPool = new ScheduledThreadPoolExecutor(1);

    public void syncApplication(){
        if (!taskSwitch()){
            return;
        }
        syncScheduledThreadPool.schedule(()->{
            SyncReq syncReq = new SyncReq();
            try {
                BeanUtils.copyProperties(syncReq,userInfo);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("flushDeviceActive copyProperties error|{}|{}", JSONUtils.beanToJson(syncReq),JSONUtils.beanToJson(userInfo));
                return;
            }
            syncService.syncApplication(syncReq);
        },getDuration(),getDurationUnit());
    }

}
