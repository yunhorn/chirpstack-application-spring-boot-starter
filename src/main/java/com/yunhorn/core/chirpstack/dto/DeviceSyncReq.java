package com.yunhorn.core.chirpstack.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author ljm
 * @date 2022/1/4 11:58
 */
@Data
public class DeviceSyncReq extends SyncReq{
    private List<String> applicationNames = Collections.emptyList();
    private String targetDeviceProfileName;
}
