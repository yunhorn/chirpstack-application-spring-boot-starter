package com.yunhorn.core.chirpstack.client.request.gateway;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 11:57
 */
@Data
public class GatewayPostReq {
    private Gateway gateway;
    public GatewayPostReq(){}
    public GatewayPostReq(Gateway gateway){this.gateway = gateway;}
}
