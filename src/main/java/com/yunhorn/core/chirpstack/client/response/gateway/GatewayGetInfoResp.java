package com.yunhorn.core.chirpstack.client.response.gateway;

import com.yunhorn.core.chirpstack.client.request.gateway.Gateway;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 15:54
 */
@Data
public class GatewayGetInfoResp {
    private String createdAt;
    private String firstSeenAt;
    private Gateway gateway;
    private String lastSeenAt;
    private String updatedAt;
}
