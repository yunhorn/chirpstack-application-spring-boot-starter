package com.yunhorn.core.chirpstack.client.response.gateway;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 11:46
 */
@Data
public class Location {
//    "accuracy":0,
//            "altitude":0,
//            "latitude":0,
//            "longitude":0,
//            "source":"UNKNOWN"
    private int accuracy = 0;
    private int altitude = 0;
    private int latitude = 0;
    private int longitude = 0;
    private String source = "UNKNOWN";
}
