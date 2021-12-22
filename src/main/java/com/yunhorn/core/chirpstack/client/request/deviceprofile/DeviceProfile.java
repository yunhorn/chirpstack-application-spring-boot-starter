package com.yunhorn.core.chirpstack.client.request.deviceprofile;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/24 16:36
 */
@Data
public class DeviceProfile {
//    "classBTimeout":0,
//            "classCTimeout":0,
//            "factoryPresetFreqs":[
//            0
//            ],
//            "geolocBufferTTL":0,
//            "geolocMinBufferSize":0,
//            "id":"string",
//            "macVersion":"string",
//            "maxDutyCycle":0,
//            "maxEIRP":0,
//            "name":"string",
//            "networkServerID":"string",
//            "organizationID":"string",
//            "payloadCodec":"string",
//            "payloadDecoderScript":"string",
//            "payloadEncoderScript":"string",
//            "pingSlotDR":0,
//            "pingSlotFreq":0,
//            "pingSlotPeriod":0,
//            "regParamsRevision":"string",
//            "rfRegion":"string",
//            "rxDROffset1":0,
//            "rxDataRate2":0,
//            "rxDelay1":0,
//            "rxFreq2":0,
//            "supports32BitFCnt":true,
//            "supportsClassB":true,
//            "supportsClassC":true,
//            "supportsJoin":true
    private int classBTimeout = 0;
    private int classCTimeout = 0;
    private List<Integer> factoryPresetFreqs = Lists.newArrayList(0);
    private int geolocBufferTTL = 0;
    private int geolocMinBufferSize = 0;
    private String id;
    private String macVersion;
    private int maxDutyCycle = 0;
    private int maxEIRP = 0;
    private String name;
    private String networkServerID;
    private String organizationID;
    private String payloadCodec;
    private String payloadDecoderScript;
    private String payloadEncoderScript;
    private int pingSlotDR = 0;
    private int pingSlotFreq = 0;
    private int pingSlotPeriod = 0;
    private String regParamsRevision;
    private String rfRegion;
    private int rxDROffset1 = 0;
    private int rxDataRate2 = 0;
    private int rxDelay1 = 0;
    private int rxFreq2 = 0;
    private boolean supports32BitFCnt = true;
    private boolean supportsClassB = true;
    private boolean supportsClassC = true;
    private boolean supportsJoin = true;
}
