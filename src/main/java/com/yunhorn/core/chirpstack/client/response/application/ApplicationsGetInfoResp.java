package com.yunhorn.core.chirpstack.client.response.application;

import com.yunhorn.core.chirpstack.client.request.application.Application;
import lombok.Data;

/**
 * @author ljm
 * @date 2021/3/1 15:35
 */
@Data
public class ApplicationsGetInfoResp {
    private Application application;
}
