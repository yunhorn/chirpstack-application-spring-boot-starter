package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 10:26
 */
@Data
public class GatewayProfileGetResult {
//    "createdAt": "2021-02-24T02:14:35.159Z",
//            "id": "string",
//            "name": "string",
//            "networkServerID": "string",
//            "networkServerName": "string",
//            "updatedAt": "2021-02-24T02:14:35.160Z"
    private String createdAt;
    private String id;
    private String name;
    private String networkServerID;
    private String networkServerName;
    private String updatedAt;
}
