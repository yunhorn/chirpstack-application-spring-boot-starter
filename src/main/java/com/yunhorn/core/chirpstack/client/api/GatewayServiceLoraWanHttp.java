//package com.yunhorn.core.chirpstack.client.api;
//
//import com.google.common.collect.Maps;
//import com.yunhorn.core.chirpstack.client.request.gateway.Gateway;
//import com.yunhorn.core.chirpstack.client.request.gateway.GatewayGetReq;
//import com.yunhorn.core.chirpstack.client.request.gateway.GatewayPostReq;
//import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetInfoResp;
//import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetResp;
//import com.yunhorn.core.chirpstack.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/25 11:38
// */
//@Service
//@Slf4j
//public class GatewayServiceLoraWanHttp extends BaseServiceLoraWanHttp {
//    private final String API_PATH = "/api/gateways";
//
//    /**
//     * GET /api/gateways
//     */
//    public GatewayGetResp get(GatewayGetReq gatewayGetReq, String domain, String account, String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (gatewayGetReq!=null){
//
//        }
//        GatewayGetResp gatewayGetResp = new GatewayGetResp();
//        try {
//            gatewayGetResp = (GatewayGetResp) JSONUtils.jsonToBean(resp,GatewayGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean GatewayGetResp error",e);
//        }
//        return gatewayGetResp;
//    }
//
////    POST /api/gateways
//    public boolean post(String domain, String account, String password, GatewayPostReq gatewayPostReq){
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (gatewayPostReq!=null && gatewayPostReq.getGateway()!=null){
//            Gateway gatewayReq = gatewayPostReq.getGateway();
//            String gatewayReqJson = JSONUtils.beanToJson(gatewayReq);
//            Map<String, Object> gateway = JSONUtils.jsonToMap(gatewayReqJson);
//            reqMap.put("gateway",gateway);
//        }else {
//            return false;
//        }
//
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/gateways",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("Gateway post error:{}",errorMsg);
//            return false;
//        }
//        return true;
//    }
//
//    public GatewayGetInfoResp get(String id, String domain, String account, String password){
//        Map<String, String> params = Maps.newHashMap();
//        params.put("id",id);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/gateways/"+id,headerMap,params);
//        GatewayGetInfoResp gatewayGetInfoResp = new GatewayGetInfoResp();
//        try {
//            gatewayGetInfoResp = (GatewayGetInfoResp) JSONUtils.jsonToBean(resp,GatewayGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean GatewayGetInfoResp error",e);
//        }
//        return gatewayGetInfoResp;
//    }
//}
