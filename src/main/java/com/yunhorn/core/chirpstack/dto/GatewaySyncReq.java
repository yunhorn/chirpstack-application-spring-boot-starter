package com.yunhorn.core.chirpstack.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2022/3/28 14:24
 */
@Data
public class GatewaySyncReq extends SyncReq{
    private String targetServiceProfileName;
}
