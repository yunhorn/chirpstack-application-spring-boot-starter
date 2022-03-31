package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfile;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 14:42
 */
@Data
public class GatewayProfileGetInfoResp {
    private String createdAt;
    private GatewayProfile gatewayProfile;
    private String updatedAt;
}
