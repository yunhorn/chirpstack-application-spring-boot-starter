package com.yunhorn.core.chirpstack.client.request.application;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/24 14:28
 */
@Data
public class ApplicationsGetReq {
//    limit
//    Max number of applications to return in the result-test.
//
//            query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    organizationID
//    ID of the organization to filter on.
//
//            query	string
//    search
//    Search on name (optional).
//
//    query	string
    private String limit;
    private String offset;
    private String organizationID;
    private String search;
}
