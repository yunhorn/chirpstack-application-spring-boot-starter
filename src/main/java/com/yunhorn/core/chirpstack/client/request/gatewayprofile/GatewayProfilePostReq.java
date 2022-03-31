package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 10:52
 */
@Data
public class GatewayProfilePostReq {
//    {
//        "gatewayProfile": {
//        "channels": [
//        0
//    ],
//        "extraChannels": [
//        {
//            "bandwidth": 0,
//                "bitrate": 0,
//                "frequency": 0,
//                "modulation": "LORA",
//                "spreadingFactors": [
//            0
//        ]
//        }
//    ],
//        "id": "string",
//                "name": "string",
//                "networkServerID": "string",
//                "statsInterval": "string"
//    }
//    }
    private GatewayProfile gatewayProfile;
    public GatewayProfilePostReq(){}
    public GatewayProfilePostReq(GatewayProfile gatewayProfile){this.gatewayProfile = gatewayProfile;}
}
