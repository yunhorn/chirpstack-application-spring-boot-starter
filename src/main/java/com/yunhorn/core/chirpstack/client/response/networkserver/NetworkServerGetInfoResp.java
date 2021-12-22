package com.yunhorn.core.chirpstack.client.response.networkserver;

import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServer;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 15:23
 */
@Data
public class NetworkServerGetInfoResp {
    private String createdAt;
    private NetworkServer networkServer;
    private String region;
    private String updatedAt;
    private String version;
}
