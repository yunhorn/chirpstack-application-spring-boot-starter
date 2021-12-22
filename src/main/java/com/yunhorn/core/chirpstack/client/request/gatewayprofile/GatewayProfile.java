package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 10:53
 */
@Data
public class GatewayProfile {
//     "channels":[
//             0
//             ],
//             "extraChannels":[
//    {
//        "bandwidth":0,
//            "bitrate":0,
//            "frequency":0,
//            "modulation":"LORA",
//            "spreadingFactors":[
//        0
//                ]
//    }
//        ],
//                "id":"string",
//                "name":"string",
//                "networkServerID":"string",
//                "statsInterval":"string"
    private List<Integer> channels = Lists.newArrayList(0);
    private List<ExtraChannel> extraChannels;
    private String id;
    private String name;
    private String networkServerID;
    private String statsInterval;
}
