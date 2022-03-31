package com.yunhorn.core.chirpstack.client.response.networkserver;

import com.yunhorn.core.chirpstack.client.response.BaseGetResp;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 14:07
 */
@Data
public class NetworkServerGetResp extends BaseGetResp {
    private List<NetworkServerGetResult> result;
}
