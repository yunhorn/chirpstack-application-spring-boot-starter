package com.yunhorn.core.chirpstack.client.request.device;

import com.yunhorn.core.chirpstack.client.response.device.DeviceKeys;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 9:55
 */
@Data
public class DeviceKeysPostReq {
    private DeviceKeys deviceKeys;

    public DeviceKeysPostReq(){

    }
    public DeviceKeysPostReq(DeviceKeys deviceKeys){
        this.deviceKeys = deviceKeys;
    }
}
