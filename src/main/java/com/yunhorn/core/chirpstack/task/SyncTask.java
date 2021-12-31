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

/**
 * @author ljm
 * @date 2021/12/31 14:23
 */
@Component
@Slf4j
public class SyncTask extends BaseTask{

    @Autowired
    private SyncService syncService;

    @Autowired
    private UserInfo userInfo;

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void syncApplication() {
        if (!taskSwitch() && !execute("flushDeviceActive",1, GlobalHelper.MINUTE)) {
            return;
        }
        SyncReq syncReq = new SyncReq();
        try {
            BeanUtils.copyProperties(syncReq,userInfo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("flushDeviceActive copyProperties error|{}|{}", JSONUtils.beanToJson(syncReq),JSONUtils.beanToJson(userInfo));
            return;
        }
        syncService.syncApplication(syncReq);
    }

}
