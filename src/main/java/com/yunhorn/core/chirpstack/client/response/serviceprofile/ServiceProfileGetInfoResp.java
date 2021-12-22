package com.yunhorn.core.chirpstack.client.response.serviceprofile;

import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfile;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 15:10
 */
@Data
public class ServiceProfileGetInfoResp {
    private String createdAt;
    private ServiceProfile serviceProfile;
    private String updatedAt;
}
