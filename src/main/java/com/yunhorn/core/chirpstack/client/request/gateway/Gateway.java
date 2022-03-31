package com.yunhorn.core.chirpstack.client.request.gateway;

import com.yunhorn.core.chirpstack.client.response.gateway.Location;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 11:58
 */
@Data
@Slf4j
public class Gateway {
//    "boards":[
//    {
//        "fineTimestampKey":"string",
//            "fpgaID":"string"
//    }
//        ],
//                "description":"string",
//                "discoveryEnabled":true,
//                "gatewayProfileID":"string",
//                "id":"string",
//                "location":{
//        "accuracy":0,
//                "altitude":0,
//                "latitude":0,
//                "longitude":0,
//                "source":"UNKNOWN"
//    },
//            "metadata":{
//
//    },
//            "name":"string",
//            "networkServerID":"string",
//            "organizationID":"string",
//            "tags":{
//
//    }
    private List<Board> boards;
    private String description;
    private boolean discoveryEnabled = true;
    private String gatewayProfileID;
    private String id;
    private Location location;
//    metadata
    private String name;
    private String networkServerID;
    private String organizationID;
    private String serviceProfileID;
//    tags

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        Gateway thisGateway = this;
        Gateway thatGateway = (Gateway) o;
        //organizationID serviceProfileID networkServerID gatewayProfileID不比较
        return thatGateway.getBoards().stream().filter(board->thisGateway.getBoards().contains(board)).count() == thisGateway.getBoards().size()
                && Optional.ofNullable(thisGateway.getId()).orElse("").equals(Optional.ofNullable(thatGateway.getId()).orElse(""))
                && Optional.ofNullable(thisGateway.getDescription()).orElse("").equals(Optional.ofNullable(thatGateway.getDescription()).orElse(""))
                && thisGateway.isDiscoveryEnabled()==thatGateway.isDiscoveryEnabled()
                && Optional.ofNullable(thisGateway.getLocation()).orElse(new Location()).equals(Optional.ofNullable(thatGateway.getLocation()).orElse(new Location()))
                && Optional.ofNullable(thisGateway.getName()).orElse("").equals(Optional.ofNullable(thatGateway.getName()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(boards, description, discoveryEnabled, location, name);
    }

    public Gateway copyProperties(String targetOrganizationID, String targetNetworkServerID, String targetServiceProfileId,String targetGatewayProfileID){
        Gateway targetGateway = new Gateway();
        try {
            BeanUtils.copyProperties(targetGateway,this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("CopyProperties sourceGateway to targetGateway error",e);
        }
        targetGateway.setOrganizationID(targetOrganizationID);
        targetGateway.setNetworkServerID(targetNetworkServerID);
        targetGateway.setServiceProfileID(targetServiceProfileId);
        targetGateway.setGatewayProfileID(targetGatewayProfileID);
        return targetGateway;
    }
}
