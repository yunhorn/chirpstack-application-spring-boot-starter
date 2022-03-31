package com.yunhorn.core.chirpstack.client.api;

import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServerGetReq;
import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServerPostReq;
import com.yunhorn.core.chirpstack.client.request.networkserver.NetworkServerPutReq;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerGetInfoResp;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerGetResp;
import com.yunhorn.core.chirpstack.client.response.networkserver.NetworkServerPostResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ljm
 * @date 2021/2/25 14:03
 */
@Service
@Slf4j
public class NetworkServerServiceLoraWanHttp extends BaseServiceLoraWanHttp {
    private final String API_PATH = "/api/network-servers";

    /**
     * GET /api/network-servers
     */
    public NetworkServerGetResp get(NetworkServerGetReq networkServerGetReq, String domain, String account, String password){
        return sendHttpsGet(networkServerGetReq,domain,API_PATH,account,password,NetworkServerGetResp.class);
    }

    /**
     * POST /api/network-servers
     */
    public NetworkServerPostResp post(String domain, String account, String password, NetworkServerPostReq networkServerPostReq){
        return sendHttpsPost(domain,API_PATH,account,password,null,networkServerPostReq,NetworkServerPostResp.class);
    }

    /**
     * PUT /api/network-servers/{network_server.id}
     */
    public boolean put(NetworkServerPutReq networkServerPutReq,String domain, String account, String password){
        if (networkServerPutReq==null || networkServerPutReq.getNetworkServer().getId()==null){
            return false;
        }
        try {
            String resp = sendHttpsPut(domain,API_PATH+"/"+networkServerPutReq.getNetworkServer().getId(),account,password,null,networkServerPutReq,String.class);
            if (resp.contains("error")){
                log.error("Update gateway error,reqObj:{}|respMsg:{}", JSONUtils.beanToJson(networkServerPutReq),resp);
                return false;
            }
            log.info("Update gateway success,reqObj:{}|respMsg:{}",JSONUtils.beanToJson(networkServerPutReq),resp);
        }catch (Exception e){
            log.error("Update gateway error,reqObj:"+JSONUtils.beanToJson(networkServerPutReq),e);
            return false;
        }
        return true;
    }

    /**
     * GET /api/network-servers/{id}
     */
    public NetworkServerGetInfoResp get(String id, String domain, String account, String password){
        return sendHttpsGet(domain,API_PATH+"/"+id,account,password,null,null,NetworkServerGetInfoResp.class);
    }
}
