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
    @Value("${chirpStack.source.account:}")
    private String sourceAccount;
    @Value("${chirpStack.source.password:}")
    private String sourcePassword;
    @Value("${chirpStack.source.domain:}")
    private String sourceDomain;
    @Value("${chirpStack.source.organizationName:}")
    private String sourceOrganizationName;
    @Value("${chirpStack.target.account:}")
    private String targetAccount;
    @Value("${chirpStack.target.password:}")
    private String targetPassword;
    @Value("${chirpStack.target.domain:}")
    private String targetDomain;
    @Value("${chirpStack.target.organizationName:}")
    private String targetOrganizationName;
    @Value("${chirpStack.target.serviceProfile:}")
    private String targetServiceProfile;
}
