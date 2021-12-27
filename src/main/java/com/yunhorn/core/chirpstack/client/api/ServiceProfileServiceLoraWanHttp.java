package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfileGetReq;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ljm
 * @date 2021/2/25 14:55
 */
@Service
@Slf4j
public class ServiceProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {

    private final String API_PATH = "/api/service-profiles";

    /**
     * GET /api/service-profiles
     */
    public ServiceProfileGetResp get(ServiceProfileGetReq serviceProfileGetReq, String domain, String account, String password){
        Map<String, String> params = Maps.newHashMap();
        if (serviceProfileGetReq!=null){
            if (StringUtils.isNotBlank(serviceProfileGetReq.getLimit())){
                params.put("limit",serviceProfileGetReq.getLimit());
            }
            if (StringUtils.isNotBlank(serviceProfileGetReq.getOffset())){
                params.put("offset",serviceProfileGetReq.getOffset());
            }
            if (StringUtils.isNotBlank(serviceProfileGetReq.getOrganizationID())){
                params.put("organizationID",serviceProfileGetReq.getOrganizationID());
            }
            if (StringUtils.isBlank(serviceProfileGetReq.getLimit()) && StringUtils.isBlank(serviceProfileGetReq.getOffset())){
                ServiceProfileGetResp serviceProfileGetResp = sendHttpsGet(domain,API_PATH,account,password,null,params,ServiceProfileGetResp.class);
                String totalCount = serviceProfileGetResp.getTotalCount();
                params.put("limit",totalCount);
            }
        }
        return sendHttpsGet(domain,API_PATH,account,password,null,params,ServiceProfileGetResp.class);
    }

//    POST /api/service-profiles
//    public ServiceProfilePostResp post(String domain, String account, String password, ServiceProfilePostReq serviceProfilePostReq){
//        ServiceProfilePostResp serviceProfilePostResp = new ServiceProfilePostResp();
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (serviceProfilePostReq!=null && serviceProfilePostReq.getServiceProfile()!=null){
//            ServiceProfile serviceProfileReq = serviceProfilePostReq.getServiceProfile();
//            String serviceProfileJson = JSONUtils.beanToJson(serviceProfileReq);
//            Map<String, Object> serviceProfile = JSONUtils.jsonToMap(serviceProfileJson);
//            reqMap.put("serviceProfile",serviceProfile);
//        }else {
//            return serviceProfilePostResp;
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/service-profiles",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("ServiceProfile post error:{}",errorMsg);
//            return serviceProfilePostResp;
//        }
//        try {
//            serviceProfilePostResp = (ServiceProfilePostResp) JSONUtils.jsonToBean(resp,ServiceProfilePostResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean ServiceProfilePostResp error",e);
//        }
//        return serviceProfilePostResp;
//    }
//
//    public ServiceProfileGetInfoResp get(String id,String domain,String account,String password){
//        ServiceProfileGetInfoResp serviceProfileGetInfoResp = new ServiceProfileGetInfoResp();
//        Map<String, String> params = Maps.newHashMap();
//        params.put("id",id);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/service-profiles/"+id,headerMap,params);
//        try {
//            serviceProfileGetInfoResp = (ServiceProfileGetInfoResp) JSONUtils.jsonToBean(resp,ServiceProfileGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean ServiceProfileGetInfoResp error",e);
//        }
//        return serviceProfileGetInfoResp;
//    }
}
