//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfile;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfileGetReq;
//import com.smartoilets.api.service.lorawan.request.serviceprofile.ServiceProfilePostReq;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfileGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfileGetResp;
//import com.smartoilets.api.service.lorawan.response.serviceprofile.ServiceProfilePostResp;
//import com.smartoilets.common.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/25 14:55
// */
//@Service
//@Slf4j
//public class ServiceProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {
//
////    GET /api/service-profiles
//    public ServiceProfileGetResp get(ServiceProfileGetReq serviceProfileGetReq,String domain, String account, String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (serviceProfileGetReq!=null){
//            String serviceProfileGetReqJson = JSONUtils.beanToJson(serviceProfileGetReq);
//            params = JSONUtils.jsonToStrMap(serviceProfileGetReqJson);
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/service-profiles",headerMap,params);
//        ServiceProfileGetResp serviceProfileGetResp = new ServiceProfileGetResp();
//        try {
//            serviceProfileGetResp = (ServiceProfileGetResp) JSONUtils.jsonToBean(resp,ServiceProfileGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean ServiceProfileGetResp error",e);
//        }
//        return serviceProfileGetResp;
//    }
//
////    POST /api/service-profiles
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
//}
