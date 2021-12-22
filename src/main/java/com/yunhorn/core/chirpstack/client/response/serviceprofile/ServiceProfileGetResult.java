package com.yunhorn.core.chirpstack.client.response.serviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:57
 */
@Data
public class ServiceProfileGetResult {
//"createdAt": "2021-02-24T02:14:35.300Z",
//        "id": "string",
//        "name": "string",
//        "networkServerID": "string",
//        "organizationID": "string",
//        "updatedAt": "2021-02-24T02:14:35.300Z"
    private String createdAt;
    private String id;
    private String name;
    private String networkServerID;
    private String organizationID;
    private String updatedAt;
}
