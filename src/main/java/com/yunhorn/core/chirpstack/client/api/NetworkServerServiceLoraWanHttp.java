//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServer;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServerGetReq;
//import com.smartoilets.api.service.lorawan.request.networkserver.NetworkServerPostReq;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerGetInfoResp;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerGetResp;
//import com.smartoilets.api.service.lorawan.response.networkserver.NetworkServerPostResp;
//import com.smartoilets.common.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/25 14:03
// */
//@Service
//@Slf4j
//public class NetworkServerServiceLoraWanHttp extends BaseServiceLoraWanHttp {
////    GET /api/network-servers
//    public NetworkServerGetResp get(NetworkServerGetReq networkServerGetReq, String domain, String account, String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (networkServerGetReq!=null){
//            String networkServerGetReqJson = JSONUtils.beanToJson(networkServerGetReq);
//            params = JSONUtils.jsonToStrMap(networkServerGetReqJson);
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/network-servers",headerMap,params);
//        NetworkServerGetResp networkServerGetResp = new NetworkServerGetResp();
//        try {
//            networkServerGetResp = (NetworkServerGetResp) JSONUtils.jsonToBean(resp,NetworkServerGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean NetworkServerGetResp error",e);
//        }
//        return networkServerGetResp;
//    }
//
////    POST /api/network-servers
//    public NetworkServerPostResp post(String domain, String account, String password,NetworkServerPostReq networkServerPostReq){
//        NetworkServerPostResp networkServerPostResp = new NetworkServerPostResp();
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (networkServerPostReq!=null && networkServerPostReq.getNetworkServer()!=null){
//            NetworkServer networkServerReq = networkServerPostReq.getNetworkServer();
//            String networkServerJson = JSONUtils.beanToJson(networkServerReq);
//            Map<String, Object> networkServer = JSONUtils.jsonToMap(networkServerJson);
//            reqMap.put("networkServer",networkServer);
//        }else {
//            return networkServerPostResp;
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/network-servers",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("NetworkServer post error:{}",errorMsg);
//            return networkServerPostResp;
//        }
//        try {
//            networkServerPostResp = (NetworkServerPostResp) JSONUtils.jsonToBean(resp,NetworkServerPostResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean NetworkServerPostResp error",e);
//        }
//
//        return networkServerPostResp;
//    }
//
//    public NetworkServerGetInfoResp get(String id,String domain,String account,String password){
//        Map<String, String> params = Maps.newHashMap();
//        params.put("id",id);
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/network-servers/"+id,headerMap,params);
//        NetworkServerGetInfoResp networkServerGetInfoResp = new NetworkServerGetInfoResp();
//        try {
//            networkServerGetInfoResp = (NetworkServerGetInfoResp) JSONUtils.jsonToBean(resp,NetworkServerGetInfoResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean NetworkServerGetInfoResp error",e);
//        }
//        return networkServerGetInfoResp;
//    }
//}
