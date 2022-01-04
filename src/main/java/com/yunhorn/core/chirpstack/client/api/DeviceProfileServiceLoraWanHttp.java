package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.api.BaseServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.request.deviceprofile.DeviceProfileGetReq;
import com.yunhorn.core.chirpstack.client.response.deviceprofile.DeviceProfileGetResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ljm
 * @date 2021/2/24 16:18
 */
@Service
@Slf4j
public class DeviceProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {

    private final String API_PATH = "/api/device-profiles";

    /**
     * GET /api/device-profiles
     */
    public DeviceProfileGetResp get(DeviceProfileGetReq deviceProfileGetReq, String domain, String account, String password){
        Map<String, String> params = Maps.newHashMap();
        if (deviceProfileGetReq!=null){
            String deviceProfileGetReqJson = JSONUtils.beanToJson(deviceProfileGetReq);
            params = JSONUtils.jsonToStrMap(deviceProfileGetReqJson);
            if (params==null){
                return null;
            }
            if (StringUtils.isBlank(deviceProfileGetReq.getLimit()) && StringUtils.isBlank(deviceProfileGetReq.getOffset())){
                DeviceProfileGetResp deviceProfileGetResp = sendHttpsGet(domain,API_PATH,account,password,null,params,DeviceProfileGetResp.class);
                String totalCount = deviceProfileGetResp.getTotalCount();
                params.put("limit",totalCount);
            }
        }
        return sendHttpsGet(domain,API_PATH,account,password,null,params,DeviceProfileGetResp.class);
    }

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
}
