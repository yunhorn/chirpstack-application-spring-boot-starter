package com.yunhorn.core.chirpstack.task;

import com.yunhorn.core.chirpstack.config.DeviceSyncConfig;
import com.yunhorn.core.chirpstack.config.UserInfo;
import com.yunhorn.core.chirpstack.dto.ApplicationSyncReq;
import com.yunhorn.core.chirpstack.dto.DeviceSyncReq;
import com.yunhorn.core.chirpstack.dto.GatewaySyncReq;
import com.yunhorn.core.chirpstack.helper.GlobalHelper;
import com.yunhorn.core.chirpstack.helper.ThreadPoolHelper;
import com.yunhorn.core.chirpstack.sync.SyncService;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.InvocationTargetException;

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


    public void syncApplication(){
        if (taskSwitch(GlobalHelper.TASK_NAME_SYNC_APPLICATION)){
            Integer delay = getDuration(GlobalHelper.TASK_NAME_SYNC_APPLICATION);
            ThreadPoolHelper.syncScheduledThreadPool.scheduleWithFixedDelay(()->{
                ApplicationSyncReq applicationSyncReq = new ApplicationSyncReq();
                try {
                    BeanUtils.copyProperties(applicationSyncReq,userInfo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("SyncApplication copyProperties error|dest:"+JSONUtils.beanToJson(applicationSyncReq)+"|orig:"+JSONUtils.beanToJson(userInfo),new Exception("SyncApplication copyProperties error"));
                    return;
                }
                syncService.syncApplication(applicationSyncReq);
            },delay,delay,getDurationUnit(GlobalHelper.TASK_NAME_SYNC_APPLICATION));
        }
    }

    public void syncDevice(){
        if (taskSwitch(GlobalHelper.TASK_NAME_SYNC_DEVICE)){
            Integer delay = getDuration(GlobalHelper.TASK_NAME_SYNC_DEVICE);
            ThreadPoolHelper.syncScheduledThreadPool.scheduleWithFixedDelay(()->{
                DeviceSyncReq deviceSyncReq = new DeviceSyncReq();
                try {
                    BeanUtils.copyProperties(deviceSyncReq,userInfo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("SyncDevice copyProperties error|dest:"+JSONUtils.beanToJson(deviceSyncReq)+"|orig:"+JSONUtils.beanToJson(userInfo),new Exception("SyncDevice copyProperties error"));
                    return;
                }
                deviceSyncReq.setApplicationNames(deviceSyncConfig.getApplicationNames());
                syncService.syncDevice(deviceSyncReq);
            },delay,delay,getDurationUnit(GlobalHelper.TASK_NAME_SYNC_DEVICE));
        }
    }

    public void syncGateway(){
        if (taskSwitch(GlobalHelper.TASK_NAME_SYNC_GATEWAY)){
            Integer delay = getDuration(GlobalHelper.TASK_NAME_SYNC_GATEWAY);
            ThreadPoolHelper.syncScheduledThreadPool.scheduleWithFixedDelay(()->{
                GatewaySyncReq gatewaySyncReq = new GatewaySyncReq();
                try {
                    BeanUtils.copyProperties(gatewaySyncReq,userInfo);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("SyncGateway copyProperties error|dest:"+JSONUtils.beanToJson(gatewaySyncReq)+"|orig:"+JSONUtils.beanToJson(userInfo),new Exception("SyncDevice copyProperties error"));
                    return;
                }
                syncService.syncGateway(gatewaySyncReq);
            },delay,delay,getDurationUnit(GlobalHelper.TASK_NAME_SYNC_GATEWAY));
        }
    }

}
