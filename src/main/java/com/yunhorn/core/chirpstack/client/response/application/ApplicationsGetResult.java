package com.yunhorn.core.chirpstack.client.response.application;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 14:22
 */
@Data
public class ApplicationsGetResult {

//    "description": "string",
//                "id": "string",
//                "name": "string",
//                "organizationID": "string",
//                "serviceProfileID": "string",
//                "serviceProfileName": "string"
    private String description;
    private String id;
    private String name;
    private String organizationID;
    private String serviceProfileID;
    private String serviceProfileName;
}
