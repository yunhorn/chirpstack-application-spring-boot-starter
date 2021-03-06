package com.yunhorn.core.chirpstack.client.request.application;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/24 15:43
 */
@Data
@Slf4j
public class Application {
//    "description": "string",
//                "id": "string",
//                "name": "string",
//                "organizationID": "string",
//                "payloadCodec": "string",
//                "payloadDecoderScript": "string",
//                "payloadEncoderScript": "string",
//                "serviceProfileID": "string"
    private String description;
    private String id;
    private String name;
    private String organizationID;
    private String payloadCodec;
    private String payloadDecoderScript;
    private String payloadEncoderScript;
    private String serviceProfileID;
    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        Application thisApplication = this;
        Application thatApplication = (Application)o;
        //id organizationID 不用比较 ，serviceProfileID因为可能serviceProfileName不一样 不比较 只对比Application里的内容
        return Optional.ofNullable(thisApplication.getDescription()).orElse("").equals(Optional.ofNullable(thatApplication.getDescription()).orElse(""))
                && Optional.ofNullable(thisApplication.getName()).orElse("").equals(Optional.ofNullable(thatApplication.getName()).orElse(""))
                && Optional.ofNullable(thisApplication.getPayloadCodec()).orElse("").equals(Optional.ofNullable(thatApplication.getPayloadCodec()).orElse(""))
                && Optional.ofNullable(thisApplication.getPayloadDecoderScript()).orElse("").equals(Optional.ofNullable(thatApplication.getPayloadDecoderScript()).orElse(""))
                && Optional.ofNullable(thisApplication.getPayloadEncoderScript()).orElse("").equals(Optional.ofNullable(thatApplication.getPayloadEncoderScript()).orElse(""))
                && !(thisApplication.getOrganizationID()!=null && thatApplication.getOrganizationID()==null)
                && !(thisApplication.getServiceProfileID()!=null && thatApplication.getServiceProfileID()==null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, payloadCodec, payloadDecoderScript, payloadEncoderScript);
    }

    public Application copyProperties(String targetOrganizationID, String targetServiceProfileId, String id){
        Application targetApplication = new Application();
        try {
            BeanUtils.copyProperties(targetApplication,this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("CopyProperties sourceApplication to targetApplication error",e);
        }
        targetApplication.setId(id);
        targetApplication.setOrganizationID(targetOrganizationID);
        targetApplication.setServiceProfileID(targetServiceProfileId);
        return targetApplication;
    }
}
