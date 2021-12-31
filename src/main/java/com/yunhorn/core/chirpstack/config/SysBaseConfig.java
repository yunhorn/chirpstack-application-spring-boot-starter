package com.yunhorn.core.chirpstack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ljm
 * @date 2021/12/31 14:45
 */
@Data
@Configuration
public class SysBaseConfig {
    @Value("chirpStack.scheduled.enable:false")
    private Boolean enable = false;
    //配置定时任务执行周期
    @Value("chirpStack.scheduled.duration:false")
    private Integer duration = 0;//执行周期时长
    @Value("chirpStack.scheduled.duration.unit:hour")
    private String durationUnit;//执行周期单位
}
