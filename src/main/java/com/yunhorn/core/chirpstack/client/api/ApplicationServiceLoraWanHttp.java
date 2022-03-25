package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPostReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetInfoResp;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsGetReq;
import com.yunhorn.core.chirpstack.client.request.application.ApplicationsPutReq;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsGetResp;
import com.yunhorn.core.chirpstack.client.response.application.ApplicationsPostResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


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
        return sendHttpsGet(applicationsGetReq,domain,API_PATH,account,password,ApplicationsGetResp.class);
    }

    /**
     * POST /api/applications
     */
    public ApplicationsPostResp post(ApplicationsPostReq applicationsPostReq, String domain, String account, String password){
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
