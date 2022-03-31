package com.yunhorn.core.chirpstack.client.request.networkserver;

import lombok.Data;

/**
 * @author ljm
 * @date 2022/3/29 14:35
 */
@Data
public class NetworkServerPutReq {
    private NetworkServer networkServer;
    public NetworkServerPutReq(){}
    public NetworkServerPutReq(NetworkServer networkServer){
        this.networkServer = networkServer;
    }
}
