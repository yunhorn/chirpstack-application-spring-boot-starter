package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 10:59
 */
@Data
public class ExtraChannel {
//    "bandwidth":0,
//            "bitrate":0,
//            "frequency":0,
//            "modulation":"LORA",
//            "spreadingFactors":[
//            0
//            ]
    private int bandwidth = 0;
    private int bitrate = 0;
    private int frequency = 0;
    private String modulation = "LORA";
    private List<Integer> spreadingFactors = Lists.newArrayList(0);
}
