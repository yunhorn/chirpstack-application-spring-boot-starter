package com.yunhorn.core.chirpstack.client.request.organization;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:30
 */
@Data
public class OrganizationGetReq {
//    limit
//    Max number of organizations to return in the result-set.
//
//            query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    search
//    When provided, the given string will be used to search on displayName.
//
//            query	string
    private String limit;
    private String offset;
    private String search;
}
