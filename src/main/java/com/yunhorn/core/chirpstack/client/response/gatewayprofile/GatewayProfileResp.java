package com.yunhorn.core.chirpstack.client.response.gatewayprofile;

import com.yunhorn.core.chirpstack.client.request.gatewayprofile.ExtraChannel;
import lombok.Data;

import java.util.List;

/**
 * @author ljm
 * @date 2021/3/1 14:43
 */
@Data
public class GatewayProfileResp {
    private List<Integer> channels;
    private List<ExtraChannel> extraChannels;
    private String id;
    private String name;
    private String networkServerID;
}
