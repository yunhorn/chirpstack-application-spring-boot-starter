package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfileGetReq;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfilePostReq;
import com.yunhorn.core.chirpstack.client.request.gatewayprofile.GatewayProfilePutReq;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfileGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfileGetResp;
import com.yunhorn.core.chirpstack.client.response.gatewayprofile.GatewayProfilePostResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ljm
 * @date 2021/2/25 10:22
 */
@Service
@Slf4j
public class GatewayProfileServiceLoraWanHttp extends BaseServiceLoraWanHttp {

    private final String API_PATH = "/api/gateway-profiles";

    /**
     * GET /api/gateway-profiles
     */
    public GatewayProfileGetResp get(GatewayProfileGetReq gatewayProfileGetReq, String domain, String account, String password){
        return sendHttpsGet(gatewayProfileGetReq,domain,API_PATH,account,password,GatewayProfileGetResp.class);
    }

    /**
     * POST /api/gateway-profiles
     */
    public GatewayProfilePostResp post(String domain, String account, String password, GatewayProfilePostReq gatewayProfilePostReq){
        return sendHttpsPost(domain,API_PATH,account,password,null,gatewayProfilePostReq,GatewayProfilePostResp.class);
    }

    public boolean put(String domain, String account, String password, GatewayProfilePutReq gatewayProfilePutReq){
        if (gatewayProfilePutReq==null || gatewayProfilePutReq.getGatewayProfile().getId()==null){
            return false;
        }
        try {
            String resp = sendHttpsPut(domain,API_PATH+"/"+gatewayProfilePutReq.getGatewayProfile().getId(),account,password,null,gatewayProfilePutReq,String.class);
            if (resp.contains("error")){
                log.error("Update gatewayProfile error,reqObj:{}|respMsg:{}", JSONUtils.beanToJson(gatewayProfilePutReq),resp);
                return false;
            }
            log.info("Update gatewayProfile Success,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(gatewayProfilePutReq),resp);
        }catch (Exception e){
            log.error("Update gatewayProfile error,reqObj:"+JSONUtils.beanToJson(gatewayProfilePutReq),e);
            return false;
        }
        return true;
    }

    /**
     * GET /api/gateway-profiles/{id}
     */
    public GatewayProfileGetInfoResp get(String id, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+id,account,password,null,null,GatewayProfileGetInfoResp.class);
    }
}
