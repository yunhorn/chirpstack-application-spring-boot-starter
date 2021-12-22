package com.yunhorn.core.chirpstack.client.response.deviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 16:21
 */
@Data
public class DeviceProfileGetResult {
//    "createdAt": "2021-02-24T02:14:35.060Z",
//            "id": "string",
//            "name": "string",
//            "networkServerID": "string",
//            "organizationID": "string",
//            "updatedAt": "2021-02-24T02:14:35.060Z"

    private String createdAt;
    private String id;
    private String name;
    private String networkServerID;
    private String organizationID;
    private String updatedAt;
}
