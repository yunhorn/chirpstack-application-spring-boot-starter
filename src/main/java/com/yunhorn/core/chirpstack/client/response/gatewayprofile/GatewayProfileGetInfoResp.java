package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 14:42
 */
@Data
public class GatewayProfileGetInfoResp {
    private String createdAt;
    private GatewayProfileResp gatewayProfile;
    private String updatedAt;
}
