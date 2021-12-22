package com.yunhorn.core.chirpstack.client.response.networkserver;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 14:07
 */
@Data
public class NetworkServerGetResp {
    private List<NetworkServerGetResult> result;
    private String totalCount;
}
