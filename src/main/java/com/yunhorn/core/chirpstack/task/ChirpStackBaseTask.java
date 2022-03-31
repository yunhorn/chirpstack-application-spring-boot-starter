package com.yunhorn.core.chirpstack.task;

import com.yunhorn.core.chirpstack.config.ApplicationSyncConfig;
import com.yunhorn.core.chirpstack.config.DeviceSyncConfig;
import com.yunhorn.core.chirpstack.config.GatewaySyncConfig;
import com.yunhorn.core.chirpstack.helper.GlobalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author ljm
 * @date 2021/12/31 14:47
 */
@Component
public class ChirpStackBaseTask {

    @Autowired
    private DeviceSyncConfig deviceSyncConfig;

    @Autowired
    private ApplicationSyncConfig applicationSyncConfig;

    @Autowired
    private GatewaySyncConfig gatewaySyncConfig;

//    private Map<String,Integer> countDownMap = Maps.newHashMap();//存倒计时的map

    public boolean taskSwitch(String taskName) {
        if (GlobalHelper.TASK_NAME_SYNC_APPLICATION.equals(taskName)){
            return applicationSyncConfig.isApplicationEnable();
        }else if (GlobalHelper.TASK_NAME_SYNC_DEVICE.equals(taskName)){
            return deviceSyncConfig.isDeviceEnable();
        }else if (GlobalHelper.TASK_NAME_SYNC_GATEWAY.equals(taskName)){
            return gatewaySyncConfig.isGatewayEnable();
        }
        return false;
    }

    public Integer getDuration(String taskName){
        if (GlobalHelper.TASK_NAME_SYNC_APPLICATION.equals(taskName)){
            return applicationSyncConfig.getDuration();
        }else if (GlobalHelper.TASK_NAME_SYNC_DEVICE.equals(taskName)){
            return deviceSyncConfig.getDuration();
        }else if (GlobalHelper.TASK_NAME_SYNC_GATEWAY.equals(taskName)){
            return gatewaySyncConfig.getDuration();
        }
        return 0;
    }

    public TimeUnit getDurationUnit(String taskName){
        if (GlobalHelper.TASK_NAME_SYNC_APPLICATION.equals(taskName)){
            return applicationSyncConfig.getDurationUnit();
        }else if (GlobalHelper.TASK_NAME_SYNC_DEVICE.equals(taskName)){
            return deviceSyncConfig.getDurationUnit();
        }else if (GlobalHelper.TASK_NAME_SYNC_GATEWAY.equals(taskName)){
            return gatewaySyncConfig.getDurationUnit();
        }
        return null;

    }


//    public boolean execute(String taskName,Integer taskDuration,String taskDurationUnit){
//        Integer duration = sysBaseConfig.getDuration();
//        String durationUnit = sysBaseConfig.getDurationUnit();
//        Integer count;//分钟为单位存储倒计时
//        if (!countDownMap.containsKey(taskName)){
//            if (GlobalHelper.MINUTE.equalsIgnoreCase(durationUnit)){
//                if (GlobalHelper.MINUTE.equalsIgnoreCase(taskDurationUnit)){
//                    count = duration;
//                }else {
//                    return false;
//                }
//            }else if (GlobalHelper.HOUR.equalsIgnoreCase(durationUnit)){
//                if (GlobalHelper.MINUTE.equalsIgnoreCase(taskDurationUnit)){
//                    count = duration * 60;
//                }else if (GlobalHelper.HOUR.equalsIgnoreCase(taskDurationUnit)) {
//                    count = duration;
//                }else {
//                    return false;
//                }
//            }else if (GlobalHelper.DAY.equalsIgnoreCase(durationUnit)){
//                if (GlobalHelper.MINUTE.equalsIgnoreCase(taskDurationUnit)){
//                    count = duration * 24 * 60;
//                }else if (GlobalHelper.HOUR.equalsIgnoreCase(taskDurationUnit)){
//                    count = duration * 24;
//                }else if (GlobalHelper.DAY.equalsIgnoreCase(taskDurationUnit)){
//                    count = duration;
//                }else {
//                    return false;
//                }
//            }else {
//                return false;
//            }
//            countDownMap.put(taskName,count);
//        }
//        count = countDownMap.get(taskName);
//        if (GlobalHelper.MINUTE.equalsIgnoreCase(taskDurationUnit)){
//            count = count - taskDuration;
//        }else if (GlobalHelper.HOUR.equalsIgnoreCase(taskDurationUnit)) {
//            count = count - taskDuration * 60;
//        }else if (GlobalHelper.DAY.equalsIgnoreCase(taskDurationUnit)){
//            count = count - taskDuration * 60 * 24;
//        }else {
//            return false;
//        }
//        if (count<=0){
//            countDownMap.remove(taskName);
//            return true;
//        }else {
//            countDownMap.put(taskName,count);
//            return false;
//        }
//    }
}
