package com.yunhorn.core.chirpstack.client.request.gateway;

import lombok.Data;

/**
 * @author ljm
 * @date 2021/2/25 11:59
 */
@Data
public class Board {
//     "fineTimestampKey":"string",
//             "fpgaID":"string"
    private String fineTimestampKey;
    private String fpgaID;
}
