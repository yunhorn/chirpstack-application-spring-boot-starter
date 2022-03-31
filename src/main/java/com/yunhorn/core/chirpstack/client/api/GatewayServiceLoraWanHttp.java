package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.gateway.GatewayGetReq;
import com.yunhorn.core.chirpstack.client.request.gateway.GatewayPostReq;
import com.yunhorn.core.chirpstack.client.request.gateway.GatewayPutReq;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ljm
 * @date 2021/2/25 11:38
 */
@Service
@Slf4j
public class GatewayServiceLoraWanHttp extends BaseServiceLoraWanHttp {
    private final String API_PATH = "/api/gateways";

    /**
     * GET /api/gateways
     */
    public GatewayGetResp get(GatewayGetReq gatewayGetReq, String domain, String account, String password){
        return sendHttpsGet(gatewayGetReq,domain,API_PATH,account,password,GatewayGetResp.class);
    }

    /**
     * POST /api/gateways
     */
    public boolean post(String domain, String account, String password, GatewayPostReq gatewayPostReq){
        try {
            String resp = sendHttpsPost(domain,API_PATH,account,password,null,gatewayPostReq,String.class);
            if (resp.contains("error")){
                log.error("Insert gateway error,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(gatewayPostReq),resp);
                return false;
            }
            log.info("Insert gateway Success,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(gatewayPostReq),resp);
        }catch (Exception e){
            log.error("Insert gateway error,reqObj:"+JSONUtils.beanToJson(gatewayPostReq),e);
            return false;
        }
        return true;
    }

    /**
     * PUT /api/gateways/{gateway.id}
     */
    public boolean put(GatewayPutReq gatewayPutReq,String domain, String account, String password){
        if (gatewayPutReq.getGateway()==null || gatewayPutReq.getGateway().getId()==null){
            return false;
        }
        try {
            String resp = sendHttpsPut(domain,API_PATH+"/"+gatewayPutReq.getGateway().getId(),account,password,null,gatewayPutReq,String.class);
            if (resp.contains("error")){
                log.error("Update gateway error,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(gatewayPutReq),resp);
                return false;
            }
            log.info("Update gateway success,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(gatewayPutReq),resp);
        }catch (Exception e){
            log.error("Update gateway error,reqObj:"+JSONUtils.beanToJson(gatewayPutReq),e);
            return false;
        }
        return true;
    }

    /**
     * GET /api/gateways/{id}
     */
    public GatewayGetInfoResp get(String id, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+id,account,password,null,null,GatewayGetInfoResp.class);
    }
}
