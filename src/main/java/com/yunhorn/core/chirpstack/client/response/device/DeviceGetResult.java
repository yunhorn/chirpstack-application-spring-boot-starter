package com.yunhorn.core.chirpstack.client.response.device;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 17:28
 */
@Data
public class DeviceGetResult {
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
    private String applicationID;
    private String description;
    private String devEUI;
    private String deviceProfileID;
    private String deviceProfileName;
    private int deviceStatusBattery = 0;
    private int deviceStatusBatteryLevel = 0;
    private boolean deviceStatusBatteryLevelUnavailable = true;
    private boolean deviceStatusExternalPowerSource = true;
    private int deviceStatusMargin = 0;
    private String lastSeenAt;
    private String name;
}
