package com.yunhorn.core.chirpstack.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author ljm
 * @date 2021/12/22 14:33
 */
@Data
@Configuration
public class UserInfo {
    @Value("${chirpStack.sync.source.account:}")
    private String sourceAccount;
    @Value("${chirpStack.sync.source.password:}")
    private String sourcePassword;
    @Value("${chirpStack.sync.source.domain:}")
    private String sourceDomain;
    @Value("${chirpStack.sync.source.organizationName:}")
    private String sourceOrganizationName;
    @Value("${chirpStack.sync.target.account:}")
    private String targetAccount;
    @Value("${chirpStack.sync.target.password:}")
    private String targetPassword;
    @Value("${chirpStack.sync.target.domain:}")
    private String targetDomain;
    @Value("${chirpStack.sync.target.organizationName:}")
    private String targetOrganizationName;
    @Value("${chirpStack.sync.target.serviceProfileName:}")
    private String targetServiceProfileName;
    @Value("${chirpStack.sync.target.deviceProfileName:}")
    private String targetDeviceProfileName;
}
