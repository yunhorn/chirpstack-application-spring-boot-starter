package com.yunhorn.core.chirpstack.client.response.deviceprofile;

import com.yunhorn.core.chirpstack.client.response.BaseGetResp;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/24 16:21
 */
@Data
public class DeviceProfileGetResp extends BaseGetResp {
//    {
//        "result": [
//        {
//            "createdAt": "2021-02-24T02:14:35.060Z",
//                "id": "string",
//                "name": "string",
//                "networkServerID": "string",
//                "organizationID": "string",
//                "updatedAt": "2021-02-24T02:14:35.060Z"
//        }
//  ],
//        "totalCount": "string"
//    }
    private List<DeviceProfileGetResult> result;
}
