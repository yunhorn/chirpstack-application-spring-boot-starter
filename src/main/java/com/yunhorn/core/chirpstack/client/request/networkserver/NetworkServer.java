package com.yunhorn.core.chirpstack.client.request.networkserver;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:17
 */
@Data
public class NetworkServer {
//    "caCert":"string",
//            "gatewayDiscoveryDR":0,
//            "gatewayDiscoveryEnabled":true,
//            "gatewayDiscoveryInterval":0,
//            "gatewayDiscoveryTXFrequency":0,
//            "id":"string",
//            "name":"string",
//            "routingProfileCACert":"string",
//            "routingProfileTLSCert":"string",
//            "routingProfileTLSKey":"string",
//            "server":"string",
//            "tlsCert":"string",
//            "tlsKey":"string"
    private String caCert;
    private int gatewayDiscoveryDR = 0;
    private boolean gatewayDiscoveryEnabled = true;
    private int gatewayDiscoveryInterval = 0;
    private int gatewayDiscoveryTXFrequency = 0;
    private String id;
    private String name;
    private String routingProfileCACert;
    private String routingProfileTLSCert;
    private String routingProfileTLSKey;
    private String server;
    private String tlsCert;
    private String tlsKey;

}
