package com.yunhorn.core.chirpstack.client.request.serviceprofile;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:55
 */
@Data
public class ServiceProfileGetReq {
//    limit
//    Max number of items to return.
//
//    query	string
//    offset
//    Offset in the result-set (for pagination).
//
//    query	string
//    organizationID
//    Organization id to filter on.
//
//            query	string
    private String limit;
    private String offset;
    private String organizationID;
}
