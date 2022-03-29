package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfileGetReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfilePostReq;
import com.yunhorn.core.chirpstack.client.request.serviceprofile.ServiceProfilePutReq;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.serviceprofile.ServiceProfilePostResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
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
        return sendHttpsGet(serviceProfileGetReq,domain,API_PATH,account,password,ServiceProfileGetResp.class);
    }

    /**
     * POST /api/service-profiles
     */
    public ServiceProfilePostResp post(String domain, String account, String password, ServiceProfilePostReq serviceProfilePostReq){
        return sendHttpsPost(domain,API_PATH,account,password,null,serviceProfilePostReq,ServiceProfilePostResp.class);
    }

    /**
     * PUT /api/service-profiles/{service_profile.id}
     */
    public boolean put(ServiceProfilePutReq serviceProfilePutReq,String domain, String account, String password){
        if (serviceProfilePutReq==null || serviceProfilePutReq.getServiceProfile().getId()==null){
            return false;
        }
        try {
            String resp = sendHttpsPut(domain,API_PATH+"/"+serviceProfilePutReq.getServiceProfile().getId(),account,password,null,serviceProfilePutReq,String.class);
            if (resp.contains("error")){
                log.error("Update service-profiles error,reqObj:{}|respMsg:{}", JSONUtils.beanToJson(serviceProfilePutReq),resp);
                return false;
            }
            log.info("Update service-profiles success,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(serviceProfilePutReq),resp);
        }catch (Exception e){
            log.error("Update service-profiles error,reqObj:"+JSONUtils.beanToJson(serviceProfilePutReq),e);
            return false;
        }
        return true;
    }

    /**
     * GET /api/service-profiles/{id}
     */
    public ServiceProfileGetInfoResp get(String id, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+id,account,password,null,null,ServiceProfileGetInfoResp.class);
    }
}
