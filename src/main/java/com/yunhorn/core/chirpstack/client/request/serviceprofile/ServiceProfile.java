package com.yunhorn.core.chirpstack.client.request.serviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 15:07
 */
@Data
public class ServiceProfile {
//    "addGWMetaData":true,
//            "channelMask":"string",
//            "devStatusReqFreq":0,
//            "dlBucketSize":0,
//            "dlRate":0,
//            "dlRatePolicy":"DROP",
//            "drMax":0,
//            "drMin":0,
//            "hrAllowed":true,
//            "id":"string",
//            "minGWDiversity":0,
//            "name":"string",
//            "networkServerID":"string",
//            "nwkGeoLoc":true,
//            "organizationID":"string",
//            "prAllowed":true,
//            "raAllowed":true,
//            "reportDevStatusBattery":true,
//            "reportDevStatusMargin":true,
//            "targetPER":0,
//            "ulBucketSize":0,
//            "ulRate":0,
//            "ulRatePolicy":"DROP"
    private boolean addGWMetaData = true;
    private String channelMask;
    private int devStatusReqFreq = 0;
    private int dlBucketSize = 0;
    private int dlRate = 0;
    private String dlRatePolicy = "DROP";
    private int drMax = 0;
    private int drMin = 0;
    private boolean hrAllowed = true;
    private String id;
    private int minGWDiversity = 0;
    private String name;
    private String networkServerID;
    private boolean nwkGeoLoc = true;
    private String organizationID;
    private boolean prAllowed = true;
    private boolean raAllowed = true;
    private boolean reportDevStatusBattery = true;
    private boolean reportDevStatusMargin = true;
    private int targetPER = 0;
    private int ulBucketSize = 0;
    private int ulRate = 0;
    private String ulRatePolicy = "DROP";
}
