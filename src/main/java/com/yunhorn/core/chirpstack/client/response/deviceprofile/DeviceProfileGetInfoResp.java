package com.yunhorn.core.chirpstack.client.response.deviceprofile;

import com.yunhorn.core.chirpstack.client.request.deviceprofile.DeviceProfile;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 16:14
 */
@Data
public class DeviceProfileGetInfoResp {
    private String createdAt;
    private DeviceProfile deviceProfile;
    private String updatedAt;
}
