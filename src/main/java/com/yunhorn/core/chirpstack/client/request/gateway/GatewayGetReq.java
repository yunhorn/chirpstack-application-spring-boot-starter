package com.yunhorn.core.chirpstack.client.request.gateway;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 11:40
 */
@Data
public class GatewayGetReq {
//    limit
//    Max number of nodes to return in the result-set.
//
//            query	integer
//    offset
//    Offset of the result-set (for pagination).
//
//    query	integer
//    organizationID
//    ID of the organization for which to filter on, when left blank the response will return all gateways to which the user has access to.
//
//            query	string
//    search
//    Search on name or gateway MAC (optional).
//
//    query	string
    private String limit;
    private String offset;
    private String organizationID;
    private String search;

}
