package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 10:24
 */
@Data
public class GatewayProfileGetReq {
//    limit
//    Max number of items to return.
//
//    query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    networkServerID
//    Network-server ID to filter on (optional).
//
//    query	string
    private String limit;
    private String offset;
    private String networkServerID;
}
