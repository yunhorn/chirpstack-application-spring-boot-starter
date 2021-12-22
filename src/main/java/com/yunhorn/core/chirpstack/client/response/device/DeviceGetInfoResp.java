package com.yunhorn.core.chirpstack.client.response.device;

import com.yunhorn.core.chirpstack.client.request.device.Device;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 16:34
 */
@Data
public class DeviceGetInfoResp {
    private Device device;
}
