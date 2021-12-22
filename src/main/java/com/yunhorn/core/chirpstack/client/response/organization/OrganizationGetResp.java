package com.yunhorn.core.chirpstack.client.response.organization;

import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/2/25 14:32
 */
@Data
public class OrganizationGetResp {
    private List<OrganizationGetResult> result;
    private String totalCount;
}
