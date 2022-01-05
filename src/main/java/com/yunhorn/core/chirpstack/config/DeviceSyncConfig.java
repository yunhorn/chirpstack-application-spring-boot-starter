package com.yunhorn.core.chirpstack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ljm
 * @date 2022/1/4 11:54
 */
@Data
@Configuration
public class DeviceSyncConfig extends SyncBaseConfig{
    @Value("${chirpStack.sync.scheduled.device.enable:false}")
    private boolean deviceEnable = false;
    @Value("${chirpStack.sync.scheduled.device.applicationName:}")
    private List<String> applicationNames = Collections.emptyList();//指定同步具体的application下的设备
    @Value("${chirpStack.sync.scheduled.device.removeTargetRedundantDevice:false}")
    private boolean isRemoveTargetRedundantDevice = false;//当同步两个平台的application下的device时，假设有一个设备 如果源application没有此设备，而目标application有，则是否保留此设备
    @Value("${chirpStack.sync.scheduled.device.duration:0}")
    private Integer duration = 0;//执行周期时长
    @Value("${chirpStack.sync.scheduled.device.durationUnit:}")
    private String durationUnit = null;//执行周期单位
    public TimeUnit getDurationUnit(){
        return super.getDurationUnit(durationUnit);
    }
}
