package com.yunhorn.core.chirpstack.config;

import com.yunhorn.core.chirpstack.helper.GlobalHelper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author ljm
 * @date 2021/12/31 14:45
 */
@Data
//@Configuration
public class SyncBaseConfig {
//    //配置定时任务执行周期
//    @Value("${chirpStack.sync.scheduled.duration:0}")
//    private Integer duration = 0;//执行周期时长
//    @Value("${chirpStack.sync.scheduled.durationUnit:}")
//    private String durationUnit;//执行周期单位

    public TimeUnit getDurationUnit(String durationUnit){
        if (GlobalHelper.SECOND.equalsIgnoreCase(durationUnit)){
            return TimeUnit.SECONDS;
        }else if (GlobalHelper.MINUTE.equalsIgnoreCase(durationUnit)){
            return TimeUnit.MINUTES;
        }else if (GlobalHelper.HOUR.equalsIgnoreCase(durationUnit)){
            return TimeUnit.HOURS;
        }else if (GlobalHelper.DAY.equalsIgnoreCase(durationUnit)){
            return TimeUnit.DAYS;
        }else {
            return null;
        }
    }
}
