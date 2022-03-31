package com.yunhorn.core.chirpstack.client.request.serviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2022/3/29 10:42
 */
@Data
public class ServiceProfilePutReq {
    private ServiceProfile serviceProfile;
    public ServiceProfilePutReq(){}
    public ServiceProfilePutReq(ServiceProfile serviceProfile){
        this.serviceProfile = serviceProfile;
    }
}
