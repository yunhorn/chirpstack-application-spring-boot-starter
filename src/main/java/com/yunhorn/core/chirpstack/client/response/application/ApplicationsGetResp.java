package com.yunhorn.core.chirpstack.client.response.application;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/24 14:20
 */
@Data
public class ApplicationsGetResp {
//    {
//        "result": [
//        {
//            "description": "string",
//                "id": "string",
//                "name": "string",
//                "organizationID": "string",
//                "serviceProfileID": "string",
//                "serviceProfileName": "string"
//        }
//  ],
//        "totalCount": "string"
//    }
    private List<ApplicationsGetResult> result;
    private String totalCount;
}
