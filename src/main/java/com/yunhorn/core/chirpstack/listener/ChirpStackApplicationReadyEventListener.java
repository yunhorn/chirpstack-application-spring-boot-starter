package com.yunhorn.core.chirpstack.listener;

import com.yunhorn.core.chirpstack.config.SysBaseConfig;
import com.yunhorn.core.chirpstack.config.UserInfo;
import com.yunhorn.core.chirpstack.task.ChirpStackSyncTask;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author ljm
 * @date 2021/12/31 16:04
 */
@Component
@Slf4j
public class ChirpStackApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private SysBaseConfig sysBaseConfig;

    @Autowired
    private ChirpStackSyncTask chirpStackSyncTask;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        log.info("chirpStack-syncer applicationReadyEvent begin");

        Map<String,String> userInfoMap = JSONUtils.jsonToStrMap(JSONUtils.beanToJson(userInfo));
        for (Map.Entry<String, String> userInfoEntry : userInfoMap.entrySet()) {
            String userInfoName = userInfoEntry.getKey();
            String userInfoValue = userInfoEntry.getValue();
            if (StringUtils.isBlank(userInfoValue)){
                log.error(userInfoName+" is not configured",new Exception("UserInfo Incomplete configuration"));
                return;
            }
        }
        if (sysBaseConfig.getDurationUnit()==null){
            log.error("SysBaseConfig lack of DurationUnit",new Exception("SysBaseConfig Incomplete configuration"));
            return;
        }else if (sysBaseConfig.getDuration()<=0){
            log.error("SysBaseConfig of Duration less than or equal to 0",new Exception("SysBaseConfig Incomplete configuration"));
            return;
        }
        log.info("chirpStack-syncer Check the configuration passed!");
        if (sysBaseConfig.isApplicationEnable() && StringUtils.isBlank(userInfo.getTargetServiceProfileName())){
            //如果开启了application同步 但没有配置同步application到目标平台要用到的ServiceProfileName 则打印异常堆栈
            log.error("Sync application lack of targetServiceProfileName");
            return;
        }if (sysBaseConfig.isDeviceEnable() && StringUtils.isBlank(userInfo.getTargetDeviceProfileName())){
            //如果开启了device同步 但没有配置同步device到目标平台要用到的DeviceProfileName 则打印异常堆栈
            log.error("Sync device lack of targetDeviceProfileName");
            return;
        }
        chirpStackSyncTask.syncApplication();
        chirpStackSyncTask.syncDevice();
    }
}
