package com.yunhorn.core.chirpstack.client.request.application;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 15:41
 */
@Data
public class ApplicationsPostReq {
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
    public ApplicationsPostReq(){

    }
    public ApplicationsPostReq(Application application){
        this.application = application;
    }
}
