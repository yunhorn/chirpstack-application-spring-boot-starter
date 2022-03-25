package com.yunhorn.core.chirpstack.client.response.serviceprofile;

import com.yunhorn.core.chirpstack.client.response.BaseGetResp;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 14:56
 */
@Data
public class ServiceProfileGetResp extends BaseGetResp {
    private List<ServiceProfileGetResult> result;
}
