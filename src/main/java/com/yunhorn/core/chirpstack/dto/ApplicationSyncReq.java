package com.yunhorn.core.chirpstack.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2022/1/4 11:56
 */
@Data
public class ApplicationSyncReq extends SyncReq{
    private String targetServiceProfileName;
}
