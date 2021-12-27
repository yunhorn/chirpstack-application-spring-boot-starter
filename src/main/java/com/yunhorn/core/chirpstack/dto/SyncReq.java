package com.yunhorn.core.chirpstack.dto;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/12/27 12:02
 */
@Data
public class SyncReq {
    private String sourceAccount;
    private String sourcePassword;
    private String sourceDomain;
    private String sourceOrganizationName;
    private String targetAccount;
    private String targetPassword;
    private String targetDomain;
    private String targetOrganizationName;
    private String targetServiceProfileName;
}
