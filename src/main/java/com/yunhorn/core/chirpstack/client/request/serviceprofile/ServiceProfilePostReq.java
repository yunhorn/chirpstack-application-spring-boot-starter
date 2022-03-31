package com.yunhorn.core.chirpstack.client.request.serviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 15:07
 */
@Data
public class ServiceProfilePostReq {
    private ServiceProfile serviceProfile;
    public ServiceProfilePostReq(){}
    public ServiceProfilePostReq(ServiceProfile serviceProfile){
        this.serviceProfile = serviceProfile;
    }
}
