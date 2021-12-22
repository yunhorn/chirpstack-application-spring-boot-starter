package com.yunhorn.core.chirpstack.client.request.organization;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:39
 */
@Data
public class Organization {
//    "canHaveGateways":true,
//            "displayName":"string",
//            "id":"string",
//            "maxDeviceCount":0,
//            "maxGatewayCount":0,
//            "name":"string"
    private boolean canHaveGateways = true;
    private String displayName;
    private String id;
//    private int maxDeviceCount = 0;
//    private int maxGatewayCount = 0;
    private String name;
}
