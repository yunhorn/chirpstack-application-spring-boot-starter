package com.yunhorn.core.chirpstack.client.response.gateway;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 11:41
 */
@Data
public class GatewayGetResp {
//    {
//        "result": [
//        {
//            "createdAt": "2021-02-24T02:14:35.177Z",
//                "description": "string",
//                "firstSeenAt": "2021-02-24T02:14:35.177Z",
//                "id": "string",
//                "lastSeenAt": "2021-02-24T02:14:35.177Z",
//                "location": {
//            "accuracy": 0,
//                    "altitude": 0,
//                    "latitude": 0,
//                    "longitude": 0,
//                    "source": "UNKNOWN"
//        },
//            "name": "string",
//                "networkServerID": "string",
//                "organizationID": "string",
//                "updatedAt": "2021-02-24T02:14:35.177Z"
//        }
//  ],
//        "totalCount": "string"
//    }
    private List<GatewayGetResult> result;
    private String totalCount;
}
