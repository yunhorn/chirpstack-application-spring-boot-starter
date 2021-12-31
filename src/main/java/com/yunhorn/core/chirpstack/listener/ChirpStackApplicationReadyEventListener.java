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

        Map<String,String> userInfoMap = JSONUtils.jsonToStrMap(JSONUtils.beanToJson(userInfo));
        for (Map.Entry<String, String> userInfoEntry : userInfoMap.entrySet()) {
            String userInfoName = userInfoEntry.getKey();
            String userInfoValue = userInfoEntry.getValue();
            if (StringUtils.isBlank(userInfoValue)){
                log.error("{} is not configured",userInfoName);
                throw new RuntimeException("UserInfo Incomplete configuration");
            }
        }
        if (sysBaseConfig.getDurationUnit()==null){
            log.error("SysBaseConfig lack of DurationUnit");
            throw new RuntimeException("SysBaseConfig Incomplete configuration");
        }else if (sysBaseConfig.getDuration()<=0){
            log.error("SysBaseConfig of Duration less than or equal to 0");
            throw new RuntimeException("SysBaseConfig Incomplete configuration");
        }
        chirpStackSyncTask.syncApplication();
    }
}
