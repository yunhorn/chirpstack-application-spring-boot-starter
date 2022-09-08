package com.yunhorn.core.chirpstack;

import com.google.common.collect.Maps;
import com.yunhorn.core.chirpstack.client.api.GatewayServiceLoraWanHttp;
import com.yunhorn.core.chirpstack.client.response.gateway.GatewayGetInfoResp;
import com.yunhorn.core.chirpstack.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author ljm
 * @date 2022/9/8 16:40
 */
@Slf4j
public class CheckExpireTokenTest {

    @Test
    public void simulateTokenExpireUseGateWay(){
        String domain = System.getProperty("chirpstack.test.domain","");
        String account = System.getProperty("chirpstack.test.account","");
        String password = System.getProperty("chirpstack.test.password","");
        String gateWayId = System.getProperty("chirpstack.test.gateWayId","");
        if (StringUtils.isBlank(domain) || StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return;
        }
        String expireToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhcyIsImV4cCI6MTY0ODYyMTI3MiwiaWQiOjEsImlzcyI6ImFzIiwibmJmIjoxNjQ4NTM0ODcyLCJzdWIiOiJ1c2VyIiwidXNlcm5hbWUiOiJhZG1pbiJ9.AmOgBy0FX3JF1ps1GM7bUJAHtC--WT2Z31BuTGIU_54";
        GatewayServiceLoraWanHttp gatewayServiceLoraWanHttp = new GatewayServiceLoraWanHttp();
        Map<String,String> headerMap = Maps.newHashMap();
        headerMap.put("Grpc-Metadata-Authorization","Bearer "+ expireToken);
        GatewayGetInfoResp gatewayGetInfoResp = gatewayServiceLoraWanHttp.get(gateWayId,domain,account,password,headerMap);
        log.info("result:{}", JSONUtils.beanToJson(gatewayGetInfoResp));
    }
}
