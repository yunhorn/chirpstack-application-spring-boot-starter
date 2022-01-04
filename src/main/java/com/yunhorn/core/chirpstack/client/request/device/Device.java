package com.yunhorn.core.chirpstack.client.request.device;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/24 17:48
 */
@Data
@Slf4j
public class Device {

//        "applicationID": "string",
//                "description": "string",
//                "devEUI": "string",
//                "deviceProfileID": "string",
//                "isDisabled": true,
//                "name": "string",
//                "referenceAltitude": 0,
//                "skipFCntCheck": true,
//                "tags": {},
//        "variables": {}
    private String applicationID;
    private String description;
    private String devEUI;
    private String deviceProfileID;
    private boolean isDisabled = false;
    private String name;
    private int referenceAltitude = 0;
    private boolean skipFCntCheck = true;
    private Map<String,String> tags;//name value
    private Map<String,String> variables;//name value

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        Device thisDevice = this;
        Device thatDevice = (Device) o;
        Map<String,String> thisTags = Maps.newHashMap(Optional.ofNullable(thisDevice.getTags()).orElse(Collections.emptyMap()));
        Map<String,String> thatTags = Maps.newHashMap(Optional.ofNullable(thatDevice.getTags()).orElse(Collections.emptyMap()));
        Map<String,String> thisVariables = Maps.newHashMap(Optional.ofNullable(thisDevice.getVariables()).orElse(Collections.emptyMap()));
        Map<String,String> thatVariables = Maps.newHashMap(Optional.ofNullable(thatDevice.getVariables()).orElse(Collections.emptyMap()));
        //applicationID deviceProfileID不用比较
        return Optional.ofNullable(thisDevice.getDescription()).orElse("").equals(Optional.ofNullable(thatDevice.getDescription()).orElse(""))
                && Optional.ofNullable(thisDevice.getDevEUI()).orElse("").equals(Optional.ofNullable(thatDevice.getDevEUI()).orElse(""))
                && Optional.ofNullable(thisDevice.getName()).orElse("").equals(Optional.ofNullable(thatDevice.getName()).orElse(""))
                && thisDevice.isDisabled()==thatDevice.isDisabled()
                && thisDevice.getReferenceAltitude()==thatDevice.getReferenceAltitude()
                && thisDevice.isSkipFCntCheck()==thatDevice.isSkipFCntCheck()
                && thisTags.size()==thatTags.size()
                && thisVariables.size()==thatVariables.size()
                && thisTags.entrySet().stream().allMatch(tagEntry-> tagEntry.getValue().equals(thatTags.get(tagEntry.getKey())))
                && thisVariables.entrySet().stream().allMatch(tagEntry-> tagEntry.getValue().equals(thatVariables.get(tagEntry.getKey())));
    }

    public Device copyProperties(String applicationID,String targetDeviceProfileID){
        Device device = new Device();
        try {
            BeanUtils.copyProperties(device,this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("CopyProperties sourceDevice to targetDevice error",e);
        }
        device.setDeviceProfileID(targetDeviceProfileID);
        device.setApplicationID(applicationID);
        return device;
    }
}
