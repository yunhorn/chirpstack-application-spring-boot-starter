package com.yunhorn.core.chirpstack.client.request.gateway;

import com.yunhorn.core.chirpstack.client.response.gateway.Location;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 11:58
 */
@Data
public class Gateway {
//    "boards":[
//    {
//        "fineTimestampKey":"string",
//            "fpgaID":"string"
//    }
//        ],
//                "description":"string",
//                "discoveryEnabled":true,
//                "gatewayProfileID":"string",
//                "id":"string",
//                "location":{
//        "accuracy":0,
//                "altitude":0,
//                "latitude":0,
//                "longitude":0,
//                "source":"UNKNOWN"
//    },
//            "metadata":{
//
//    },
//            "name":"string",
//            "networkServerID":"string",
//            "organizationID":"string",
//            "tags":{
//
//    }
    private List<Board> boards;
    private String description;
    private boolean discoveryEnabled = true;
    private String gatewayProfileID;
    private String id;
    private Location location;
//    metadata
    private String name;
    private String networkServerID;
    private String organizationID;
    private String serviceProfileID;
//    tags

}
