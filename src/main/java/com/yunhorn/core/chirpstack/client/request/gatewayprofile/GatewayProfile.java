package com.yunhorn.core.chirpstack.client.request.gatewayprofile;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 10:53
 */
@Data
@Slf4j
public class GatewayProfile {
//     "channels":[
//             0
//             ],
//             "extraChannels":[
//    {
//        "bandwidth":0,
//            "bitrate":0,
//            "frequency":0,
//            "modulation":"LORA",
//            "spreadingFactors":[
//        0
//                ]
//    }
//        ],
//                "id":"string",
//                "name":"string",
//                "networkServerID":"string",
//                "statsInterval":"string"
    private List<Integer> channels = Lists.newArrayList(0);
    private List<ExtraChannel> extraChannels;
    private String id;
    private String name;
    private String networkServerID;
//    private String statsInterval;

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        GatewayProfile thisGatewayProfile = this;
        GatewayProfile thatGatewayProfile = (GatewayProfile) o;
        //id networkServerID不比较
        return (thisGatewayProfile.getChannels().size()==thatGatewayProfile.getChannels().size() && thatGatewayProfile.getChannels().containsAll(thisGatewayProfile.getChannels()))
                && (Optional.ofNullable(thatGatewayProfile.getExtraChannels()).orElse(Collections.emptyList()).containsAll(Optional.ofNullable(thisGatewayProfile.getExtraChannels()).orElse(Collections.emptyList())))
                && Optional.ofNullable(thisGatewayProfile.getName()).orElse("").equals(Optional.ofNullable(thatGatewayProfile.getName()).orElse(""));
//                && Optional.ofNullable(thisGatewayProfile.getStatsInterval()).orElse("").equals(Optional.ofNullable(thatGatewayProfile.getStatsInterval()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(channels, extraChannels, name);
    }

    public GatewayProfile copyProperties(String targetNetworkServerID,String targetId){
        GatewayProfile gatewayProfile = new GatewayProfile();
        try {
            BeanUtils.copyProperties(gatewayProfile,this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("CopyProperties sourceGatewayProfile to targetGatewayProfile error",e);
        }
        gatewayProfile.setId(targetId);
        gatewayProfile.setNetworkServerID(targetNetworkServerID);
        return gatewayProfile;
    }
}
