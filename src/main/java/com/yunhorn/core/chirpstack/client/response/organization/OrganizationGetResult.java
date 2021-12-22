package com.yunhorn.core.chirpstack.client.response.organization;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:32
 */
@Data
public class OrganizationGetResult {
//    "canHaveGateways": true,
//            "createdAt": "2021-02-24T02:14:35.268Z",
//            "displayName": "string",
//            "id": "string",
//            "name": "string",
//            "updatedAt": "2021-02-24T02:14:35.268Z"
    private boolean canHaveGateways = true;
    private String createdAt;
    private String displayName;
    private String id;
    private String name;
    private String updatedAt;

}
