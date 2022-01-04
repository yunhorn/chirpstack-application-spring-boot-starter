package com.yunhorn.core.chirpstack.client.request.device;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 17:32
 */
@Data
public class DeviceGetReq {
//    limit
//    Max number of devices to return in the result-set.
//
//            query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    applicationID
//    Application ID to filter on.
//
//            query	string
//    search
//    Search on name or DevEUI.
//
//            query	string
//    multicastGroupID
//    Multicast-group ID to filter on (string formatted UUID).
//
//    query	string
//    serviceProfileID
//    Service-profile ID to filter on (string formatted UUID).
//
//    query	string
    private String limit;
    private String offset;
    private String applicationID;
    private String search;
    private String multicastGroupID;
    private String serviceProfileID;
    public DeviceGetReq(){

    }
    public DeviceGetReq(String applicationID){
        this.applicationID = applicationID;
    }
}
