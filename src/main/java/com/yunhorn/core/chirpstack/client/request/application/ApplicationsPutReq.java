package com.yunhorn.core.chirpstack.client.request.application;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/12/22 17:54
 */
@Data
public class ApplicationsPutReq {
//    {
//        "application": {
//        "description": "string",
//                "id": "string",
//                "name": "string",
//                "organizationID": "string",
//                "payloadCodec": "string",
//                "payloadDecoderScript": "string",
//                "payloadEncoderScript": "string",
//                "serviceProfileID": "string"
//    }
//    }
    private Application application;
}
