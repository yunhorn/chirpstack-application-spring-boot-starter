package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import com.yunhorn.core.chirpstack.client.response.BaseGetResp;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 10:26
 */
@Data
public class GatewayProfileGetResp extends BaseGetResp {

    private List<GatewayProfileGetResult> result;
}
