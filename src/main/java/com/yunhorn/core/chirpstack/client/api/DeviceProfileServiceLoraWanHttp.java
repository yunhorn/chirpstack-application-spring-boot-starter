//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/24 16:18
// */
//@Service
//@Slf4j
//public class DeviceProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {
//
//    /**
//     * GET /api/device-profiles
//     */
//    public DeviceProfileGetResp get(DeviceProfileGetReq deviceProfileGetReq,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (deviceProfileGetReq!=null){
//            if (StringUtils.isNotBlank(deviceProfileGetReq.getLimit())){
//                params.put("limit",deviceProfileGetReq.getLimit());
//            }
//            if (StringUtils.isNotBlank(deviceProfileGetReq.getOffset())){
//                params.put("offset",deviceProfileGetReq.getOffset());
//            }
//            if (StringUtils.isNotBlank(deviceProfileGetReq.getOrganizationID())){
//                params.put("organizationID",deviceProfileGetReq.getOrganizationID());
//            }
//            if (StringUtils.isNotBlank(deviceProfileGetReq.getApplicationID())){
//                params.put("applicationID",deviceProfileGetReq.getApplicationID());
//            }
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/device-profiles",headerMap,params);
//        DeviceProfileGetResp deviceProfileGetResp = new DeviceProfileGetResp();
//        try {
//            deviceProfileGetResp = (DeviceProfileGetResp) JSONUtils.jsonToBean(resp,DeviceProfileGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceProfileGetResp error",e);
//        }
//        return deviceProfileGetResp;
//    }
//
//    /**
//     * POST /api/device-profiles
//     */
//    public DeviceProfilePostResp post(DeviceProfilePostReq deviceProfilePostReq,String domain,String account,String password){
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (deviceProfilePostReq!=null && deviceProfilePostReq.getDeviceProfile()!=null){
//            DeviceProfile deviceProfileReq = deviceProfilePostReq.getDeviceProfile();
//            String deviceProfileJson = JSONUtils.beanToJson(deviceProfileReq);
//            Map<String, Object> deviceProfile = JSONUtils.jsonToMap(deviceProfileJson);
//            reqMap.put("deviceProfile",deviceProfile);
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/device-profiles",headerMap,reqMap);
//        DeviceProfilePostResp deviceProfilePostResp = new DeviceProfilePostResp();
//        try {
//            deviceProfilePostResp = (DeviceProfilePostResp) JSONUtils.jsonToBean(resp,DeviceProfilePostResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceProfilePostResp error",e);
//        }
//        return deviceProfilePostResp;
//    }
//
//    public DeviceProfileGetInfoResp get(String id,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        params.put("id",id);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/device-profiles/"+id,headerMap,params);
//        DeviceProfileGetInfoResp deviceProfileGetInfoResp = new DeviceProfileGetInfoResp();
//        try {
//            deviceProfileGetInfoResp = (DeviceProfileGetInfoResp) JSONUtils.jsonToBean(resp,DeviceProfileGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean DeviceProfileGetInfoResp error",e);
//        }
//        return deviceProfileGetInfoResp;
//    }
//}
