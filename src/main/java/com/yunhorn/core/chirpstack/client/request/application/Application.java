package com.yunhorn.core.chirpstack.client.request.application;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 15:43
 */
@Data
public class Application {
//    "description": "string",
//                "id": "string",
//                "name": "string",
//                "organizationID": "string",
//                "payloadCodec": "string",
//                "payloadDecoderScript": "string",
//                "payloadEncoderScript": "string",
//                "serviceProfileID": "string"
    private String description;
    private String id;
    private String name;
    private String organizationID;
    private String payloadCodec;
    private String payloadDecoderScript;
    private String payloadEncoderScript;
    private String serviceProfileID;
}
