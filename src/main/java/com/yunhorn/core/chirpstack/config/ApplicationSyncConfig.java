package com.yunhorn.core.chirpstack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author ljm
 * @date 2022/1/5 10:24
 */
@Data
@Configuration
public class ApplicationSyncConfig extends SyncBaseConfig{
    @Value("${chirpStack.sync.scheduled.application.enable:false}")
    private boolean applicationEnable = false;
    @Value("${chirpStack.sync.scheduled.application.duration:0}")
    private Integer duration = 0;//执行周期时长
    @Value("${chirpStack.sync.scheduled.application.durationUnit:}")
    private String durationUnit = null;//执行周期单位
    public TimeUnit getDurationUnit(){
        return super.getDurationUnit(durationUnit);
    }
}
