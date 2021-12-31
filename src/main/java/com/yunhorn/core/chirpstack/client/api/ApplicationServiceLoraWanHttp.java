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

    private final String API_PATH = "/api/applications";

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
                ApplicationsGetResp applicationsGetResp = sendHttpsGet(domain,API_PATH,account,password,null,params,ApplicationsGetResp.class);
                String totalCount = applicationsGetResp.getTotalCount();
                params.put("limit",totalCount);
            }
        }
        return sendHttpsGet(domain,API_PATH,account,password,null,params,ApplicationsGetResp.class);
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
        return sendHttpsPost(domain,API_PATH,account,password,null,applicationsPostReq,ApplicationsPostResp.class);
    }

    /**
     * PUT /api/applications/{application.id}
     */
    public void put(ApplicationsPutReq applicationsPutReq,String domain,String account, String password){
        if (applicationsPutReq.getApplication()==null || applicationsPutReq.getApplication().getId()==null){
            return;
        }
        sendHttpsPut(domain,API_PATH+"/"+applicationsPutReq.getApplication().getId(),account,password,null,applicationsPutReq,String.class);
    }

    /**
     * GET /api/applications/{id}
     */
    public ApplicationsGetInfoResp get(String id, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+id,account,password,null,null,ApplicationsGetInfoResp.class);
    }


}
