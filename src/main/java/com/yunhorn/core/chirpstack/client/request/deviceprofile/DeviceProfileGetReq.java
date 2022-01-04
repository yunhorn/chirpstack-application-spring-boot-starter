package com.yunhorn.core.chirpstack.client.request.deviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 16:26
 */
@Data
public class DeviceProfileGetReq {
//    limit
//    Max number of items to return.
//
//    query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    organizationID
//    Organization id to filter on.
//
//            query	string
//    applicationID
//    Application id to filter on.
//
//            query	string
    private String limit;
    private String offset;
    private String organizationID;
    private String applicationID;

    public DeviceProfileGetReq(){

    }

    public DeviceProfileGetReq(String organizationID){
        this.organizationID = organizationID;
    }

}
