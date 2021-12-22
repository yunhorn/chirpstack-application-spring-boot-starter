package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 10:26
 */
@Data
public class GatewayProfileGetResp {

    private List<GatewayProfileGetResult> result;
}
