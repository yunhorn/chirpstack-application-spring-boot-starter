package com.yunhorn.core.chirpstack.client.response.networkserver;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 14:08
 */
@Data
public class NetworkServerGetResult {
//     "createdAt": "2021-02-24T02:14:35.250Z",
//             "id": "string",
//             "name": "string",
//             "server": "string",
//             "updatedAt": "2021-02-24T02:14:35.250Z"
    private String createdAt;
    private String id;
    private String name;
    private String server;
    private String updatedAt;
}
