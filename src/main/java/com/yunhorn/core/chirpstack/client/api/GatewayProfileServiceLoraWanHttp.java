//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfile;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfileGetReq;
//import com.smartoilets.api.service.lorawan.request.gatewayprofile.GatewayProfilePostReq;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfileGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfileGetResp;
//import com.smartoilets.api.service.lorawan.response.gatewayprofile.GatewayProfilePostResp;
//import com.smartoilets.common.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/25 10:22
// */
//@Service
//@Slf4j
//public class GatewayProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {
//
////    GET /api/gateway-profiles
//    public GatewayProfileGetResp get(GatewayProfileGetReq gatewayProfileGetReq,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (gatewayProfileGetReq!=null){
//            String gatewayProfileGetReqJson = JSONUtils.beanToJson(gatewayProfileGetReq);
//            params = JSONUtils.jsonToStrMap(gatewayProfileGetReqJson);
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/gateway-profiles",headerMap,params);
//        GatewayProfileGetResp gatewayProfileGetResp = new GatewayProfileGetResp();
//        try {
//            gatewayProfileGetResp = (GatewayProfileGetResp) JSONUtils.jsonToBean(resp,GatewayProfileGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean GatewayProfileGetResp error",e);
//        }
//        return gatewayProfileGetResp;
//    }
//
//    //POST /api/gateway-profiles
//    public GatewayProfilePostResp post(String domain,String account,String password,GatewayProfilePostReq gatewayProfilePostReq){
//        GatewayProfilePostResp gatewayProfilePostResp = new GatewayProfilePostResp();
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (gatewayProfilePostReq!=null && gatewayProfilePostReq.getGatewayProfile()!=null){
//            GatewayProfile gatewayProfileReq = gatewayProfilePostReq.getGatewayProfile();
//            String gatewayProfileJson = JSONUtils.beanToJson(gatewayProfileReq);
//            Map<String, Object> gatewayProfile = JSONUtils.jsonToMap(gatewayProfileJson);
//            reqMap.put("gatewayProfile",gatewayProfile);
//        }else {
//            return gatewayProfilePostResp;
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/gateway-profiles",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("GatewayProfile post error:{}",errorMsg);
//            return gatewayProfilePostResp;
//        }
//        try {
//            gatewayProfilePostResp = (GatewayProfilePostResp) JSONUtils.jsonToBean(resp,GatewayProfilePostResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean GatewayProfilePostResp error",e);
//        }
//        return gatewayProfilePostResp;
//    }
//
//    public GatewayProfileGetInfoResp get(String id,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        params.put("id",id);
//        GatewayProfileGetInfoResp gatewayProfileGetInfoResp = new GatewayProfileGetInfoResp();
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/gateway-profiles/"+id,headerMap,params);
//        try {
//            gatewayProfileGetInfoResp = (GatewayProfileGetInfoResp) JSONUtils.jsonToBean(resp,GatewayProfileGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean GatewayProfileGetInfoResp error",e);
//        }
//        return gatewayProfileGetInfoResp;
//    }
//}
