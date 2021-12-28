package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.request.device.Device;
import com.yunhorn.core.chirpstack.client.request.device.DeviceGetReq;
import com.yunhorn.core.chirpstack.client.request.device.DevicePostReq;
import com.yunhorn.core.chirpstack.client.response.device.DeviceGetResp;
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

    public static void main(String[] args) {
        String domain = "";
        String account = "admin";
        String password = "admin";
        DeviceServiceLoraWanHttp deviceServiceLoraWanHttp = new DeviceServiceLoraWanHttp();
        DevicePostReq devicePostReq = new DevicePostReq();
        Device device = new Device();
        device.setApplicationID("21");
        device.setDescription("test Device");
        device.setDevEUI("ffffff100000c444");
        device.setDeviceProfileID("");
        device.setName("test Device");
        devicePostReq.setDevice(device);
        boolean b = deviceServiceLoraWanHttp.post(devicePostReq,domain,account,password,true);
        System.out.println(b);
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

//
//    public DeviceGetInfoResp get(String dev_eui,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        params.put("dev_eui",dev_eui);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/devices/"+dev_eui,headerMap,params);
//        DeviceGetInfoResp deviceGetInfoResp = new DeviceGetInfoResp();
//        try {
//            deviceGetInfoResp = (DeviceGetInfoResp) JSONUtils.jsonToBean(resp,DeviceGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceGetInfoResp error",e);
//        }
//        return deviceGetInfoResp;
//    }
//
//    /**
//     * GET /api/devices/{dev_eui}/keys
//     */
//    public DeviceKeysGetResp getDeviceKey(String dev_eui,String domain,String account,String password){
//        if (StringUtils.isBlank(dev_eui)){
//            return new DeviceKeysGetResp();
//        }
//        Map<String, String> params = Maps.newHashMap();
//        params.put("dev_eui",dev_eui);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/devices/"+dev_eui+"/keys",headerMap,params);
//        DeviceKeysGetResp deviceKeysGetResp = new DeviceKeysGetResp();
//        try {
//            deviceKeysGetResp = (DeviceKeysGetResp) JSONUtils.jsonToBean(resp,DeviceKeysGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceKeysGetResp error",e);
//        }
//        return deviceKeysGetResp;
//    }
//
////    POST /api/devices/{device_keys.dev_eui}/keys
//    public boolean postDeviceKey(String domain,String account,String password,String dev_eui, DeviceKeysPostReq deviceKeysPostReq,boolean deleteWhenExist){
//        if (deviceKeysPostReq==null || deviceKeysPostReq.getDeviceKeys()==null){
//            return false;
//        }
//        Map<String,Map> reqMap = Maps.newHashMap();
//        DeviceKeys deviceKeysReq = deviceKeysPostReq.getDeviceKeys();
//        String deviceKeysJson = JSONUtils.beanToJson(deviceKeysReq);
//        Map<String, Object> deviceKeys = JSONUtils.jsonToMap(deviceKeysJson);
//        reqMap.put("deviceKeys",deviceKeys);
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/devices/"+dev_eui+"/keys",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("postDeviceKey error|dev_eui:{}|message:{}",dev_eui,errorMsg);
//            if (deleteWhenExist && "object already exists".equalsIgnoreCase(errorMsg)){
//                String delResp = HttpClientUtil.getInstance().sendHttpDelete(domain+"/api/devices/"+dev_eui+"/keys", headerMap);
//                String reInsertResp = sendHttpsPost(domain,"/api/devices",headerMap,reqMap);
//                Map<String,Object> reInsertMap = JSONUtils.jsonToMap(reInsertResp);
//                if (reInsertMap!=null && reInsertMap.containsKey("error")) {
//                    //重新添加设备还是出错
//                    log.error("LoraWanHttpDeviceService reAdd device({}) error,respMap:{}", dev_eui, reInsertMap);
//                    return false;
//                }else {
//                    log.info("reInsert device Success,device:{}",dev_eui);
//                    return true;
//                }
//            }
//            return false;
//        }
//        return true;
//    }
}
