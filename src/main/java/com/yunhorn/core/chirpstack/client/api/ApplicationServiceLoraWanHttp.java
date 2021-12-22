package com.yunhorn.core.chirpstack.client.api;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPostReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetInfoResp;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsGetReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPutReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsPostResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ljm
 * @date 2021/2/24 14:17
 */
@Service
@Slf4j
public class ApplicationServiceLoraWanHttp extends BaseServiceLoraWanHttp {

    /**
     * GET /api/applications
     */
    public ApplicationsGetResp get(ApplicationsGetReq applicationsGetReq, String domain, String account, String password){
        Map<String, String> params = Maps.newHashMap();
        if (applicationsGetReq!=null){
            if (StringUtils.isNotBlank(applicationsGetReq.getLimit())){
                params.put("limit",applicationsGetReq.getLimit());
            }
            if (StringUtils.isNotBlank(applicationsGetReq.getOffset())){
                params.put("offset",applicationsGetReq.getOffset());
            }
            if (StringUtils.isNotBlank(applicationsGetReq.getOrganizationID())){
                params.put("organizationID",applicationsGetReq.getOrganizationID());
            }
            if (StringUtils.isNotBlank(applicationsGetReq.getSearch())){
                params.put("search",applicationsGetReq.getSearch());
            }
            if (StringUtils.isBlank(applicationsGetReq.getLimit()) && StringUtils.isBlank(applicationsGetReq.getOffset())){
                ApplicationsGetResp applicationsGetResp = sendHttpsGet(domain,"/api/applications",null,params,ApplicationsGetResp.class);
                String totalCount = applicationsGetResp.getTotalCount();
                params.put("limit",totalCount);
            }
        }
        ApplicationsGetResp applicationsGetResp = sendHttpsGet(domain,"/api/applications",null,params,ApplicationsGetResp.class);
        return applicationsGetResp;
    }

    /**
     * POST /api/applications
     */
    public ApplicationsPostResp post(ApplicationsPostReq applicationsPostReq, String domain, String account, String password){
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (applicationsPostReq!=null && applicationsPostReq.getApplication()!=null){
//            Application applicationReq = applicationsPostReq.getApplication();
//            String applicationJson = JSONUtils.beanToJson(applicationReq);
//            Map<String, Object> application = JSONUtils.jsonToMap(applicationJson);
//            reqMap.put("application",application);
//        }
        ApplicationsPostResp applicationsPostResp = sendHttpsPost(domain,"/api/applications",null,applicationsPostReq,ApplicationsPostResp.class);
        return applicationsPostResp;
    }

    /**
     * PUT /api/applications/{application.id}
     */
    public void put(ApplicationsPutReq applicationsPutReq,String domain,String account, String password){
        if (applicationsPutReq.getApplication()==null || applicationsPutReq.getApplication().getId()==null){
            return;
        }
        sendHttpsPut(domain,"/api/applications/"+applicationsPutReq.getApplication().getId(),null,applicationsPutReq,String.class);
    }

    /**
     * GET /api/applications/{id}
     */
    public ApplicationsGetInfoResp get(String id, String domain, String account, String password){
        ApplicationsGetInfoResp applicationsGetInfoResp = sendHttpsGet(domain,"/api/applications/"+id,null,null,ApplicationsGetInfoResp.class);
        return applicationsGetInfoResp;
    }

    public static void main(String[] args) {
//        ApplicationsPostReq applicationsPostReq = new ApplicationsPostReq();
//        Application application = new Application();
//        application.setDescription("this is a test application");
//        application.setName("testApplication");
//        application.setServiceProfileID("663ea3c5-b29f-46e0-a67d-e67f304d92a8");
//        application.setOrganizationID("1");
//        applicationsPostReq.setApplication(application);
//        Map<String,Map> reqMap = Maps.newHashMap();
//        if (applicationsPostReq!=null && applicationsPostReq.getApplication()!=null){
//            Application applicationReq = applicationsPostReq.getApplication();
//            String applicationJson = JSONUtils.beanToJson(applicationReq);
//            Map<String, Object> applicationMap = JSONUtils.jsonToMap(applicationJson);
//            reqMap.put("application",applicationMap);
//        }
//        ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp = new ApplicationServiceLoraWanHttp();
//        ApplicationsPostResp applicationsPostResp = applicationServiceLoraWanHttp.sendHttpsPost("http://47.240.84.71:8080","/api/applications",null,applicationsPostReq,ApplicationsPostResp.class);
//        ApplicationsPostResp applicationsPostResp = applicationServiceLoraWanHttp.post(applicationsPostReq,"http://47.240.84.71:8080","admin","admin");

//        System.out.println(applicationsPostResp);

//        ApplicationsPutReq applicationsPutReq = new ApplicationsPutReq();
//        Application application = new Application();
//        application.setId("21");
//        application.setDescription("this is a test application11");
//        application.setName("testApplication11");
//        application.setServiceProfileID("663ea3c5-b29f-46e0-a67d-e67f304d92a8");
//        application.setOrganizationID("1");
//        applicationsPutReq.setApplication(application);
//        ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp = new ApplicationServiceLoraWanHttp();
//        applicationServiceLoraWanHttp.put(applicationsPutReq,"http://47.240.84.71:8080",null,null);

//        ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp = new ApplicationServiceLoraWanHttp();
//        applicationServiceLoraWanHttp.get("21","http://47.240.84.71:8080",null,null);

        ApplicationsGetReq applicationsGetReq = new ApplicationsGetReq();
        ApplicationServiceLoraWanHttp applicationServiceLoraWanHttp = new ApplicationServiceLoraWanHttp();
        applicationServiceLoraWanHttp.get(applicationsGetReq,"http://47.240.84.71:8080",null,null);
    }


}
