package com.yunhorn.core.chirpstack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author ljm
 * @date 2022/3/28 14:16
 */
@Data
@Configuration
public class GatewaySyncConfig extends SyncBaseConfig{
    @Value("${chirpStack.sync.scheduled.gateway.enable:false}")
    private boolean gatewayEnable = false;
    @Value("${chirpStack.sync.scheduled.gateway.duration:0}")
    private Integer duration = 0;//执行周期时长
    @Value("${chirpStack.sync.scheduled.gateway.durationUnit:}")
    private String durationUnit = null;//执行周期单位
    public TimeUnit getDurationUnit(){
        return super.getDurationUnit(durationUnit);
    }
}
