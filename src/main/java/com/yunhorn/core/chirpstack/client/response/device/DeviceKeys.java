package com.yunhorn.core.chirpstack.client.response.device;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 18:30
 */
@Data
public class DeviceKeys {
//     "appKey": "string",
//             "devEUI": "string",
//             "genAppKey": "string",
//             "nwkKey": "string"
    private String appKey;
    private String devEUI;
    private String genAppKey;
    private String nwkKey;
}
