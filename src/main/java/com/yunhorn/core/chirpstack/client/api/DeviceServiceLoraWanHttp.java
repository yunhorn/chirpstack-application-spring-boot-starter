package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.request.device.*;
import com.yunhorn.core.chirpstack.client.response.device.DeviceGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.device.DeviceGetResp;
import com.yunhorn.core.chirpstack.client.response.device.DeviceKeysGetResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ljm
 * @date 2021/2/24 17:23
 */
@Service
@Slf4j
public class DeviceServiceLoraWanHttp extends BaseServiceLoraWanHttp {

    private final String API_PATH = "/api/devices";

    /**
     * GET /api/devices
     */
    public DeviceGetResp get(DeviceGetReq deviceGetReq, String domain, String account, String password){
        Map<String, String> params = Maps.newHashMap();
        if (deviceGetReq!=null){
            String deviceGetReqJson = JSONUtils.beanToJson(deviceGetReq);
            params = JSONUtils.jsonToStrMap(deviceGetReqJson);
            if (params==null){
                return null;
            }
            if (StringUtils.isBlank(deviceGetReq.getLimit()) && StringUtils.isBlank(deviceGetReq.getOffset())){
                DeviceGetResp deviceGetResp = sendHttpsGet(domain,API_PATH,account,password,null,params,DeviceGetResp.class);
                String totalCount = deviceGetResp.getTotalCount();
                params.put("limit",totalCount);
            }
        }
        return sendHttpsGet(domain,API_PATH,account,password,null,params,DeviceGetResp.class);
    }

    /**
     * POST /api/devices
     */
    public boolean post(DevicePostReq devicePostReq, String domain, String account, String password, boolean deleteWhenExist){
        try {
            String resp = sendHttpsPost(domain,API_PATH,account,password,null,devicePostReq,String.class);
            log.info("Insert device Success,devEui:{}|respMsg:{}",devicePostReq.getDevice(),resp);
        }catch (Exception e){
            String errorMsg = e.getMessage();
            if (errorMsg.contains("object already exists")){
                if (deleteWhenExist){
                    try {
                        String delResp = delete(devicePostReq.getDevice().getDevEUI(), domain, account, password);
                        log.info("Delete device when exist|devEui:{}|respMsg:{}",devicePostReq.getDevice().getDevEUI(),delResp);
                    }catch (Exception deleteE) {
                        String deleteErrorMsg = deleteE.getMessage();
                        log.error("Delete device error when post exist|{}|{}", devicePostReq.getDevice().getDevEUI(), deleteErrorMsg);
                        return false;
                    }
                    try {
                        String reAddResp = sendHttpsPost(domain, API_PATH, account, password, null, devicePostReq, String.class);
                        log.info("ReAdd device|devEui:{}|respMsg:{}",devicePostReq.getDevice().getDevEUI(),reAddResp);
                        return true;
                    }catch (Exception reAddE){
                        String reAddErrorMsg = reAddE.getMessage();
                        log.error("ReAdd device({}) error|{}", devicePostReq.getDevice().getDevEUI(), reAddErrorMsg);
                        return false;
                    }
                }else {
                    log.info("Device already exists but not delete when exist|{}",devicePostReq.getDevice().getDevEUI());
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * DELETE /api/devices/{dev_eui}
     */
    public String delete(String dev_eui,String domain, String account, String password){
        return sendHttpsDelete(domain,API_PATH+"/"+dev_eui,account,password,null,null,String.class);
    }

    /**
     * PUT /api/devices/{device.dev_eui}
     */
    public String put(DevicePutReq devicePutReq,String domain, String account, String password){
        if (StringUtils.isBlank(devicePutReq.getDevice().getDevEUI())){
            log.error("Put device lack of dev_eui|{}|{}|{}|{}",JSONUtils.beanToJson(devicePutReq),domain,account,password);
            return null;
        }
        return sendHttpsPut(domain,API_PATH+"/"+devicePutReq.getDevice().getDevEUI(),account,password,null,devicePutReq,String.class);
    }


    /**
     * GET /api/devices/{dev_eui}
     */
    public DeviceGetInfoResp get(String dev_eui, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+dev_eui,account,password,null,null,DeviceGetInfoResp.class);
    }

    /**
     * GET /api/devices/{dev_eui}/keys
     */
    public DeviceKeysGetResp getDeviceKey(String dev_eui, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+dev_eui+"/"+"keys",account,password,null,null,DeviceKeysGetResp.class);
    }

    /**
     * POST /api/devices/{device_keys.dev_eui}/keys
     */
    public boolean postDeviceKey(DeviceKeysPostReq deviceKeysPostReq, String domain, String account, String password, boolean deleteWhenExist){
        String dev_eui = deviceKeysPostReq.getDeviceKeys().getDevEUI();
        if (StringUtils.isBlank(dev_eui)){
            log.error("Post deviceKey error,lack of dev_eui|{}",JSONUtils.beanToJson(deviceKeysPostReq));
            return false;
        }
        try {
            String resp = sendHttpsPost(domain,API_PATH+"/"+dev_eui+"/"+"keys",account,password,null,deviceKeysPostReq,String.class);
            log.info("Post deviceKey success|dev_eui:{}|respMsg:{}",dev_eui,resp);
        }catch (Exception postE){
            String errorMsg = postE.getMessage();
            if (errorMsg.contains("object already exists")){
                if (deleteWhenExist){
                    try {
                        String delResp = deleteDeviceKey(dev_eui, domain, account, password);
                        log.info("Delete device when exist|devEui:{}|respMsg:{}",dev_eui,delResp);
                    }catch (Exception deleteE){
                        String deleteErrorMsg = deleteE.getMessage();
                        log.error("Delete device error when post exist|{}|{}", dev_eui, deleteErrorMsg);
                        return false;
                    }
                    try {
                        String reAddResp = sendHttpsPost(domain,API_PATH+"/"+dev_eui+"/"+"keys",account,password,null,deviceKeysPostReq,String.class);
                        log.info("ReAdd device|devEui:{}|respMsg:{}",dev_eui,reAddResp);
                        return true;
                    }catch (Exception reAddE){
                        String reAddErrorMsg = reAddE.getMessage();
                        log.error("ReAdd device({}) error|{}", dev_eui, reAddErrorMsg);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * PUT /api/devices/{device_keys.dev_eui}/keys
     */
    public String putDeviceKey(DeviceKeysPutReq deviceKeysPutReq,String domain, String account, String password){
        String dev_eui = deviceKeysPutReq.getDeviceKeys().getDevEUI();
        if (StringUtils.isBlank(dev_eui)){
            log.error("Put deviceKey lack of dev_eui|{}|{}|{}|{}",JSONUtils.beanToJson(deviceKeysPutReq),domain,account,password);
            return null;
        }
        return sendHttpsPut(domain,API_PATH+"/"+dev_eui+"/"+"keys",account,password,null,deviceKeysPutReq,String.class);
    }

    /**
     * DELETE /api/devices/{dev_eui}/keys
     */
    public String deleteDeviceKey(String dev_eui,String domain, String account, String password){
        return sendHttpsDelete(domain,API_PATH+"/"+dev_eui+"/"+"keys",account,password,null,null,String.class);
    }
}
