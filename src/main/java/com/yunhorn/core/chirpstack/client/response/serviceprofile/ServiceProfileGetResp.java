package com.yunhorn.core.chirpstack.client.response.serviceprofile;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 14:56
 */
@Data
public class ServiceProfileGetResp {
    private List<ServiceProfileGetResult> result;
    private String totalCount;
}
