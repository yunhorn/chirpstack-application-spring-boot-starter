//package io.github.yunhorn.chirpstackappsyncer.client.api;
//
//import com.google.common.collect.Maps;
//import com.smartoilets.api.service.lorawan.request.organization.Organization;
//import com.smartoilets.api.service.lorawan.request.organization.OrganizationGetReq;
//import com.smartoilets.api.service.lorawan.request.organization.OrganizationPostReq;
//import com.smartoilets.api.service.lorawan.response.organization.OrganizationGetResp;
//import com.smartoilets.api.service.lorawan.response.organization.OrganizationPostResp;
//import com.smartoilets.common.util.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
///**
// * @author ljm
// * @date 2021/2/25 14:29
// */
//@Service
//@Slf4j
//public class OrganizationServiceLoraWanHttp extends BaseServiceLoraWanHttp {
////    GET /api/organizations
//    public OrganizationGetResp get(OrganizationGetReq organizationGetReq,String domain, String account, String password){
//        Map<String, String> params = Maps.newHashMap();
//        if (organizationGetReq!=null){
//            String organizationGetReqJson = JSONUtils.beanToJson(organizationGetReq);
//            params = JSONUtils.jsonToStrMap(organizationGetReqJson);
//        }
//        Map<String, String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsGet(domain,"/api/organizations",headerMap,params);
//        OrganizationGetResp organizationGetResp = new OrganizationGetResp();
//        try {
//            organizationGetResp = (OrganizationGetResp) JSONUtils.jsonToBean(resp,OrganizationGetResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean OrganizationGetResp error",e);
//        }
//        return organizationGetResp;
//    }
//
////    POST /api/organizations
//    public OrganizationPostResp post(String domain, String account, String password, OrganizationPostReq organizationPostReq){
//        OrganizationPostResp organizationPostResp = new OrganizationPostResp();
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (organizationPostReq!=null && organizationPostReq.getOrganization()!=null){
//            Organization organizationReq = organizationPostReq.getOrganization();
//            String organizationReqJson = JSONUtils.beanToJson(organizationReq);
//            Map<String,Object> organization = JSONUtils.jsonToMap(organizationReqJson);
//            reqMap.put("organization",organization);
//        }else {
//            return organizationPostResp;
//        }
//        Map<String,String> headerMap = getAuthHeadMap(domain,account,password,false);
//        String resp = sendHttpsPost(domain,"/api/organizations",headerMap,reqMap);
//        Map<String,Object> respMap = JSONUtils.jsonToMap(resp);
//        if (respMap!=null && respMap.containsKey("error")){
//            String errorMsg = (String) respMap.get("error");
//            log.error("organizations post error:{}",errorMsg);
//            return organizationPostResp;
//        }
//        try {
//            organizationPostResp = (OrganizationPostResp) JSONUtils.jsonToBean(resp,OrganizationPostResp.class);
//        }catch (Exception e){
//            log.error("jsonToBean OrganizationPostResp error",e);
//        }
//        return organizationPostResp;
//    }
//
//}
