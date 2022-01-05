package com.yunhorn.core.chirpstack.client.response.device;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/24 18:30
 */
@Data
@Slf4j
public class DeviceKeys {
//     "appKey": "string",
//             "devEUI": "string",
//             "genAppKey": "string",
//             "nwkKey": "string"
    private String appKey;
    private String devEUI;
    private String genAppKey;
    private String nwkKey;
    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        DeviceKeys thisDeviceKeys = this;
        DeviceKeys thatDeviceKeys = (DeviceKeys) o;
        return Optional.ofNullable(thisDeviceKeys.getAppKey()).orElse("").equals(Optional.ofNullable(thatDeviceKeys.getAppKey()).orElse(""))
                && Optional.ofNullable(thisDeviceKeys.getGenAppKey()).orElse("").equals(Optional.ofNullable(thatDeviceKeys.getGenAppKey()).orElse(""))
                && Optional.ofNullable(thisDeviceKeys.getDevEUI()).orElse("").equals(Optional.ofNullable(thatDeviceKeys.getDevEUI()).orElse(""))
                && Optional.ofNullable(thisDeviceKeys.getNwkKey()).orElse("").equals(Optional.ofNullable(thatDeviceKeys.getNwkKey()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(appKey, devEUI, genAppKey, nwkKey);
    }

    public DeviceKeys copyProperties(){
        DeviceKeys deviceKeys = new DeviceKeys();
        try {
            BeanUtils.copyProperties(deviceKeys,this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("CopyProperties sourceDeviceKeys to targetDeviceKeys error",e);
        }
        return deviceKeys;
    }
}
