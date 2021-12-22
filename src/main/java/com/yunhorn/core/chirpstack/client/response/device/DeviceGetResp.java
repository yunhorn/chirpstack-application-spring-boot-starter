package com.yunhorn.core.chirpstack.client.response.device;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/24 17:27
 */
@Data
public class DeviceGetResp {
//    {
//        "result": [
//        {
//            "applicationID": "string",
//                "description": "string",
//                "devEUI": "string",
//                "deviceProfileID": "string",
//                "deviceProfileName": "string",
//                "deviceStatusBattery": 0,
//                "deviceStatusBatteryLevel": 0,
//                "deviceStatusBatteryLevelUnavailable": true,
//                "deviceStatusExternalPowerSource": true,
//                "deviceStatusMargin": 0,
//                "lastSeenAt": "2021-02-24T02:14:35.093Z",
//                "name": "string"
//        }
//  ],
//        "totalCount": "string"
//    }
    private List<DeviceGetResult> result;
    private String totalCount;
}
