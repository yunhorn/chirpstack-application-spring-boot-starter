//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import com.smartoilets.api.service.lorawan.request.device.Device;
//import com.smartoilets.api.service.lorawan.request.device.DeviceGetReq;
//import com.smartoilets.api.service.lorawan.request.device.DeviceKeysPostReq;
//import com.smartoilets.api.service.lorawan.request.device.DevicePostReq;
//import com.smartoilets.api.service.lorawan.response.device.DeviceGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.device.DeviceGetResp;
//import com.smartoilets.api.service.lorawan.response.device.DeviceKeys;
//import com.smartoilets.api.service.lorawan.response.device.DeviceKeysGetResp;
//import com.smartoilets.common.util.HttpClientUtil;
//import com.smartoilets.common.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/24 17:23
// */
//@Service
//@Slf4j
//public class DeviceServiceLoraWanHttp extends BaseServiceLoraWanHttp {
//
//    /**
//     * GET /api/devices
//     */
//    public DeviceGetResp get(DeviceGetReq deviceGetReq,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (deviceGetReq!=null){
//            String deviceGetReqJson = JSONUtils.beanToJson(deviceGetReq);
//            params = JSONUtils.jsonToStrMap(deviceGetReqJson);
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/devices",headerMap,params);
//        DeviceGetResp deviceGetResp = new DeviceGetResp();
//        try {
//            deviceGetResp = (DeviceGetResp) JSONUtils.jsonToBean(resp,DeviceGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceGetResp error",e);
//        }
//
////        getToken
////        get1
////        get2
//
//        return deviceGetResp;
//    }
//
//    /**
//     * POST /api/devices
//     */
//    public boolean post(DevicePostReq devicePostReq,String domain,String account,String password,boolean deleteWhenExist){
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (devicePostReq!=null && devicePostReq.getDevice()!=null && StringUtils.isNotBlank(devicePostReq.getDevice().getDevEUI()) ){
//            Device deviceReq = devicePostReq.getDevice();
//            String deviceJson = JSONUtils.beanToJson(deviceReq);
//            Map<String, Object> device = JSONUtils.jsonToMap(deviceJson);
//            reqMap.put("device",device);
//        }else {
//            return false;
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/devices",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            if (deleteWhenExist && "object already exists".equalsIgnoreCase(errorMsg)){
//                String delResp = HttpClientUtil.getInstance().sendHttpDelete(domain+"/api/devices/"+devicePostReq.getDevice().getDevEUI(), headerMap);
//                String reInsertResp = sendHttpsPost(domain,"/api/devices",headerMap,reqMap);
//                Map<String,Object> reInsertMap = JSONUtils.jsonToMap(reInsertResp);
//                if (reInsertMap!=null && reInsertMap.containsKey("error")) {
//                    //重新添加设备还是出错
//                    log.error("LoraWanHttpDeviceService reAdd device({}) error,respMap:{}", devicePostReq.getDevice(), reInsertMap);
//                    return false;
//                }else {
//                    log.info("reInsert device Success,device:{}",devicePostReq.getDevice());
//                    return true;
//                }
//            }else {
//                log.info("deleteWhenExist is false,no reInsert device:{}",devicePostReq.getDevice());
//                return true;
//            }
//        }else {
//            log.info("insert device Success,device:{}",devicePostReq.getDevice());
//            return true;
//        }
//    }
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
//}
