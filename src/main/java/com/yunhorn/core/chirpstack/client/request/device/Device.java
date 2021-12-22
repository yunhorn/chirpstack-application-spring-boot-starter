package com.yunhorn.core.chirpstack.client.request.device;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 17:48
 */
@Data
public class Device {

//        "applicationID": "string",
//                "description": "string",
//                "devEUI": "string",
//                "deviceProfileID": "string",
//                "isDisabled": true,
//                "name": "string",
//                "referenceAltitude": 0,
//                "skipFCntCheck": true,
//                "tags": {},
//        "variables": {}
    private String applicationID;
    private String description;
    private String devEUI;
    private String deviceProfileID;
    private boolean isDisabled = false;
    private String name;
    private int referenceAltitude = 0;
    private boolean skipFCntCheck = true;

}
