package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.organization.OrganizationGetReq;
import com.yunhorn.core.chirpstack.client.response.organization.OrganizationGetResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ljm
 * @date 2021/2/25 14:29
 */
@Service
@Slf4j
public class OrganizationServiceLoraWanHttp extends BaseServiceLoraWanHttp {
    private final String API_PATH = "/api/organizations";

    /**
     * GET /api/organizations
     */
    public OrganizationGetResp get(OrganizationGetReq organizationGetReq, String domain, String account, String password) {
        return sendHttpsGet(organizationGetReq,domain,API_PATH,account,password,OrganizationGetResp.class);
    }

//    POST /api/organizations
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

}
